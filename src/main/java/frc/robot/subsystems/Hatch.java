package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.hatch.HatchWithJoy;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hatch extends PIDSubsystem {

    Solenoid piston = new Solenoid(2);
    WPI_TalonSRX hatchMotor = new WPI_TalonSRX(RobotMap.Hatch.hatchMotor);
    AbsoluteEncoder hatchEncoder = new AbsoluteEncoder(RobotMap.Hatch.hatchEncoderPort,
            RobotMap.Hatch.hatchEncoderOffset);
    // private double degrees;
    // private double hatchAngle;

    public Hatch() {
        super("Hatch", 0.007, 0, 0.0075);
        hatchMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void move() {
    }

    public void smartdash() {
        SmartDashboard.putNumber("HatchEncoder", (hatchEncoder.getAngle() * 360 / (2 * Math.PI)));
    }

    public void hatchLeft() {
        setSetpoint(270);
        enable();
    }

    public void hatchRight() {
        setSetpoint(95);
        enable();
    }

    public void hatchUp() {
        setSetpoint(180);
        enable();
    }

    public void hatchDrive() {
        setSetpoint(210);
        enable();
    }

    public void stop() {
        hatchMotor.set(0);
    }

    // public void hatchPivot(double degrees) {
    //     this.degrees = degrees;
    //     hatchAngle = hatchEncoder.getAngle();
    //     hatchMotor.set(ControlMode.MotionMagic, degrees);
    // }

    public void grab() {
        piston.set(true);
    }
    
    public void place() {
        piston.set(false);
    }

    @Override
    protected void initDefaultCommand() {
    }

    @Override
    protected double returnPIDInput() {
        return (hatchEncoder.getAngle() * 360 / (2 * Math.PI));
    }

    @Override
    protected void usePIDOutput(double output) {
        hatchMotor.set(output);
    }
}
