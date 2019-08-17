package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.ConfigParameter;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;


public class ClimbArm extends PIDSubsystem{

    CANSparkMax armMotor = new CANSparkMax(RobotMap.ClimbArm.ARM_MOTOR, MotorType.kBrushless);
    CANEncoder armEncoder = armMotor.getEncoder();
    
    boolean gyro = false;
    
    public ClimbArm() {
        super("Climb", 0.15, 0, 0.015);
        armMotor.setIdleMode(IdleMode.kCoast);
        armMotor.setSmartCurrentLimit(40);
        armMotor.burnFlash();
    }


    @Override
    public void initDefaultCommand() {
    }

    public void prepareLevel2() {
        gyro = false;
        setSetpoint(RobotMap.ClimbArm.ARM_LEVEL2);
        enable();
    }

    public void prepareLevel3() {
        gyro = false;
        setSetpoint(RobotMap.ClimbArm.ARM_LEVEL3);
        enable();
    }

    public void lift() {
        gyro = true;
        setSetpoint(-3.5);
        enable();
    }

    public void stopArm() {
        disable();
        armMotor.set(0);
    }

    public void smartDash() {
        SmartDashboard.putNumber("arm Encoder", armEncoder.getPosition());
        SmartDashboard.putNumber("arm Output", armMotor.getAppliedOutput());
    }
    @Override
    protected double returnPIDInput() {
        return gyro ? Robot.navxGyro.getTilt() : armEncoder.getPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        armMotor.set(output);
    }
}