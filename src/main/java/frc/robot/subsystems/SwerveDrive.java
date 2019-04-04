package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.swerve.*;
import frc.robot.profiling.TrapezoidProfile;
import frc.robot.profiling.ProfileFollower;
import frc.robot.util.*;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveDrive extends Subsystem implements PIDSource, PIDOutput {
    public ProfileFollower swerveMP = new ProfileFollower(.008, 0.0, 0.15, 0, 0.02, this, this);
    private Vector2D tankVector2D;
    public final SwerveModule[] modules = new SwerveModule[] {
            // front left swerve module
            new SwerveModule(new CANSparkMax(RobotMap.SwerveDrive.FL_DRIVE, MotorType.kBrushless),
                    new WPI_VictorSPX(RobotMap.SwerveDrive.FL_STEER),
                    new AbsoluteEncoder(RobotMap.SwerveDrive.FL_ENCODER, RobotMap.SwerveDrive.FL_ENC_OFFSET),
                    -RobotMap.SwerveDrive.WHEEL_BASE_WIDTH / 2, RobotMap.SwerveDrive.WHEEL_BASE_LENGTH / 2),
            // front right swerve module
            new SwerveModule(new CANSparkMax(RobotMap.SwerveDrive.FR_DRIVE, MotorType.kBrushless),
                    new WPI_VictorSPX(RobotMap.SwerveDrive.FR_STEER),
                    new AbsoluteEncoder(RobotMap.SwerveDrive.FR_ENCODER, RobotMap.SwerveDrive.FR_ENC_OFFSET),
                    RobotMap.SwerveDrive.WHEEL_BASE_WIDTH / 2, RobotMap.SwerveDrive.WHEEL_BASE_LENGTH / 2),
            // back left swerve module
            new SwerveModule(new CANSparkMax(RobotMap.SwerveDrive.BL_DRIVE, MotorType.kBrushless),
                    new WPI_VictorSPX(RobotMap.SwerveDrive.BL_STEER),
                    new AbsoluteEncoder(RobotMap.SwerveDrive.BL_ENCODER, RobotMap.SwerveDrive.BL_ENC_OFFSET),
                    -RobotMap.SwerveDrive.WHEEL_BASE_WIDTH / 2, -RobotMap.SwerveDrive.WHEEL_BASE_LENGTH / 2),
            // back right swerve module
            new SwerveModule(new CANSparkMax(RobotMap.SwerveDrive.BR_DRIVE, MotorType.kBrushless),
                    new WPI_VictorSPX(RobotMap.SwerveDrive.BR_STEER),
                    new AbsoluteEncoder(RobotMap.SwerveDrive.BR_ENCODER, RobotMap.SwerveDrive.BR_ENC_OFFSET),
                    RobotMap.SwerveDrive.WHEEL_BASE_WIDTH / 2, -RobotMap.SwerveDrive.WHEEL_BASE_LENGTH / 2) };
    private double pivX, pivY, transAngle, mpangle, gyroangle, speed = RobotMap.SwerveDrive.SPEED,
            turnRate = RobotMap.SwerveDrive.TURN_RATE;
    private boolean drivingField = true, visionOn = false;

    public SwerveDrive() {
        enable();
    }

    public void setPivot(double pivotX, double pivotY) {
        this.pivX = pivotX;
        this.pivY = pivotY;
    }

    public void setCoast() {
        for (SwerveModule module : modules)
            module.driveController.setIdleMode(IdleMode.kCoast);
    }

    public void setBrake() {
        for (SwerveModule module : modules)
            module.driveController.setIdleMode(IdleMode.kBrake);
    }

    public void debugMode() {

    }

    /**
     * Drive with field oriented capability
     * 
     * @param translationX relative speed in left/right direction (-1 to 1)
     * @param translationY relative speed in forward/reverse direction (-1 to 1)
     * @param rotation     relative rate of rotation around pivot point (-1 to 1)
     *                     positive is clockwise
     * @param heading      offset in heading in radians (used for field oriented
     *                     control)
     */
    private void driveWithOrient(double translationX, double translationY, double rotation, boolean fieldOrientation) {
        Vector2D correctOrientation = correctOrientationVector2D(translationX, translationY);
        translationX = fieldOrientation ? correctOrientation.x : translationX;
        translationY = fieldOrientation ? correctOrientation.y : translationY;
        Vector2D[] vects = new Vector2D[modules.length];
        Vector2D transVect = new Vector2D(translationX, translationY), pivotVect = new Vector2D(pivX, pivY);
        setTrans(transVect);

        // if there is only one module ignore rotation
        if (modules.length < 2)
            for (SwerveModule module : modules)
                module.set(transVect.getAngle(), Math.min(1, transVect.getMagnitude())); // cap magnitude at 1

        double maxDist = 0;
        for (int i = 0; i < modules.length; i++) {
            vects[i] = new Vector2D(modules[i].positionX, modules[i].positionY);
            vects[i].subtract(pivotVect); // calculate module's position relative to pivot point
            maxDist = Math.max(maxDist, vects[i].getMagnitude()); // find farthest distance from pivot
        }

        double maxPower = 1;
        for (int i = 0; i < modules.length; i++) {
            // rotation motion created by driving each module perpendicular to
            // the vector from the pivot point
            vects[i].makePerpendicular();
            // scale by relative rate and normalize to the farthest module
            // i.e. the farthest module drives with power equal to 'rotation' variable
            vects[i].scale(rotation / maxDist);
            vects[i].add(transVect);
            // calculate largest power assigned to modules
            // if any exceed 100%, all must be scale down
            maxPower = Math.max(maxPower, vects[i].getMagnitude());
        }

        double power;
        for (int i = 0; i < modules.length; i++) {
            power = vects[i].getMagnitude() / maxPower; // scale down by the largest power that exceeds 100%
            if (power > .05) {
                setTrans(vects[i]);
                modules[i].set(vects[i].getAngle() - Math.PI / 2, power);
            } else {
                modules[i].rest();
            }
        }
    }

    /*****************************************************************************************/
    public void driveWithVision(double yaw, boolean forwards) {
        double magX = (OI.joy1.getRawAxis(1) * speed) / 100;
        double magY = (-OI.joy1.getRawAxis(0) * speed) / 100;
        Vector2D visionVect = new Vector2D(magX, magY);
        if (forwards) {
            if (Robot.tape == true && yaw != 0) {
                driveWithOrient(visionVect.getMagnitude(), 0,
                        ((yaw / Math.abs(yaw)) * (Math.sqrt(Math.abs(yaw)))) / 100, false);
            } else {
                driveWithOrient(visionVect.getMagnitude(), 0, 0, false);
            }
        } else {
            if (Robot.tape == true && yaw != 0) {
                driveWithOrient(visionVect.getMagnitude() * (-1), 0,
                        ((yaw / Math.abs(yaw)) * (Math.sqrt(Math.abs(yaw)))) / 100, false);
            } else {
                driveWithOrient(visionVect.getMagnitude() * (-1), 0, 0, false);
            }
        }
    }

    /*****************************************************************************************/
    /*****************************************************************************************/
    public void autoDriveWithVision(double yaw, boolean forwards, double autoSpeed) {
        if (forwards) {
            if (Robot.tape == true && yaw != 0) {
                driveWithOrient(autoSpeed / 100, 0, ((yaw / Math.abs(yaw)) * ((Math.sqrt(Math.abs(yaw)))) / 100) * 1.25,
                        false);
            } else {
                driveWithOrient(autoSpeed / 100, 0, 0, false);
            }
        } else {
            if (Robot.tape == true && yaw != 0) {
                driveWithOrient(autoSpeed / 100 * (-1), 0,
                        ((yaw / Math.abs(yaw)) * ((Math.sqrt(Math.abs(yaw)))) / 100) * 1.25, false);
            } else {
                driveWithOrient(autoSpeed / 100 * (-1), 0, 0, false);
            }
        }
    }

    /*****************************************************************************************/

    public void setTrans(Vector2D vector) {
        this.tankVector2D = vector;
    }

    public void setTransAngle(Vector2D vector) {
        this.transAngle = vector.getAngle();
    }

    public double getTransAngle() {
        return transAngle;
    }

    public Vector2D getTrans() {
        return tankVector2D;
    }

    /**
     * Regular robot oriented control.
     * 
     * @param translationX relative speed in left/right direction (-1 to 1)
     * @param translationY relative speed in forward/reverse direction (-1 to 1)
     * @param rotation     relative rate of rotation around pivot point (-1 to 1)
     *                     positive is clockwise
     */

    private Vector2D correctOrientationVector2D(double x, double y) {
        double angle = Robot.navxGyro.getAngle() * Math.PI / 180;
        return new Vector2D(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle));
    }

    public void driveNormal(double translationX, double translationY, double rotation) {
        driveWithOrient(translationX, translationY, rotation, false);
    }

    public void driveField(double translationX, double translationY, double rotation) {
        driveWithOrient(translationX, translationY, rotation, true);
    }

    public void enable() {
        for (SwerveModule module : modules)
            module.enable();
    }

    public void disable() {
        for (SwerveModule module : modules)
            module.disable();
    }

    public void lockWheels() {
        modules[0].set(45, 0);
        modules[1].set(-45, 0);
        modules[2].set(-45, 0);
        modules[3].set(45, 0);
    }

    public void setSpeed(double drive, double rotation) {
        this.speed = drive;
        this.turnRate = rotation;
    }

    public void setField() {
        this.drivingField = !drivingField;
    }

    public void setVision() {
        this.visionOn = !visionOn;
    }

    public void move() {
        double x = (OI.joy1.getRawAxis(0));
        double y = (OI.joy1.getRawAxis(1));
        double z = (OI.joy1.getRawAxis(4));
        
        if ((Math.abs(x) > .1 || Math.abs(y) > .1 || Math.abs(z) > .1) && !drivingField)
            driveNormal((x * speed) / 100, (-y * speed) / 100, (z * turnRate / 100));

        else if ((Math.abs(x) > .1 || Math.abs(y) > .1 || Math.abs(z) > .1) && drivingField)
            driveField((x * speed) / 100, (-y * speed) / 100, (z * turnRate / 100));

        else if (Robot.follower.isFinished()) {
            driveNormal(0, 0, 0);
        }
    }

    public void stop(int module) {
        modules[module].driveController.set(0);
        modules[module].steerController.set(0);
    }

    public void smartDash() {
        SmartDashboard.putNumber("FL", modules[0].getAngle() * 360 / (2 * Math.PI));
        SmartDashboard.putNumber("FR", modules[1].getAngle() * 360 / (2 * Math.PI));
        SmartDashboard.putNumber("BL", modules[2].getAngle() * 360 / (2 * Math.PI));
        SmartDashboard.putNumber("BR", modules[3].getAngle() * 360 / (2 * Math.PI));
        SmartDashboard.putNumber("FLDrive", modules[1].getDistance());
        SmartDashboard.putBoolean("FieldOrient", drivingField);
        SmartDashboard.putNumber("Gyro", Robot.navxGyro.getAngle());
    }

    public void runProfile(double angle, TrapezoidProfile profile) {
        modules[0].resetEncoder();
        modules[1].resetEncoder();
        modules[2].resetEncoder();
        modules[3].resetEncoder();
        swerveMP.startProfile(profile);
        mpangle = angle;
        gyroangle = Robot.navxGyro.getAngle();
    }

    @Override
    public void pidWrite(double output) {
        driveNormal(output * Math.sin(mpangle * Math.PI / 180), output * Math.cos(mpangle * Math.PI / 180),
                ((gyroangle - Robot.navxGyro.getAngle()) * 0.01));
    }

    @Override
    public double pidGet() {
        return ((Math.abs(modules[0].getDistance()) + Math.abs(modules[1].getDistance())
                + Math.abs(modules[2].getDistance()) + Math.abs(modules[3].getDistance())) / 4);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new SwerveDriveWithJoy());
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return null;
    }
}