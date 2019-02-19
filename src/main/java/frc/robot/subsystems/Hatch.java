package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.hatch.HatchWithJoy;
import frc.robot.profiling.TrapezoidProfile;
import frc.robot.profiling.ProfileFollower;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hatch extends PIDSubsystem {

    WPI_TalonSRX hatchMotor = new WPI_TalonSRX(RobotMap.Hatch.hatchMotor);
    AbsoluteEncoder hatchEncoder = new AbsoluteEncoder(RobotMap.Hatch.hatchEncoderPort,RobotMap.Hatch.hatchEncoderOffset);
    private double degrees;
    private double hatchAngle;

    public Hatch() {
        super("Hatch", 0.01, 0, 0.01);
        hatchMotor.setNeutralMode(NeutralMode.Brake);
        // // Set Motion Magic gains in slot0 - see documentation
        // hatchMotor.selectProfileSlot(0, 0);
        // hatchMotor.config_kF(0, 0, 0);
        // hatchMotor.config_kP(0, 0, 0);
        // hatchMotor.config_kI(0, 0, 0);
        // hatchMotor.config_kD(0, 0, 0);
    }

    public void move() {
        disable();
        if (Math.abs(OI.joy2.getRawAxis(4)) > 0.1) {
            hatchMotor.set(OI.joy2.getRawAxis(4)*0.5);
        } else {
            hatchMotor.set(0);
        }
    }

    public void smartdash() {
        SmartDashboard.putNumber("HatchEncoder", (hatchEncoder.getAngle()* 360 / (2 * Math.PI)));
}
    public void stop() {
        hatchMotor.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HatchWithJoy());
    }

    public void hatchLeft() {
        setSetpoint(35);
        enable();
    }

    public boolean joyPosition() {
        return Math.abs(OI.joy2.getRawAxis(4)) > .05;
    }

    public void hatchRight() {
        setSetpoint(140);
        enable();
    }

    public void hatchUp() {
        setSetpoint(95);
        enable();
    }

    public void pincherPivot(double degrees) {
        this.degrees = degrees;
        hatchAngle = hatchEncoder.getAngle();
        hatchMotor.set(ControlMode.MotionMagic, degrees);
    }

    // @Override
    // public void setSetpoint(double setpoint) {
    //     setpoint = wrapAngle(setpoint);
    //     super.setSetpoint(setpoint);
    // }
    
    // private double wrapAngle(double angle) {
    //     angle %= 2 * Math.PI;
    //     if (angle < 0)
    //         angle += 2 * Math.PI;
    //     return angle;
    // }

    @Override
    protected double returnPIDInput() {
        return (hatchEncoder.getAngle()* 360 / (2 * Math.PI));
    }

    @Override
    protected void usePIDOutput(double output) {
        hatchMotor.set(output);
    }
}
