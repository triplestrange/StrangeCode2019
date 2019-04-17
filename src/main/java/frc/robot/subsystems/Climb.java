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


public class Climb extends PIDSubsystem{

    CANSparkMax stiltMotor = new CANSparkMax(RobotMap.ClimbStilt.STILT_MOTOR, MotorType.kBrushless);
    CANPIDController stiltPID = stiltMotor.getPIDController();
    CANEncoder stiltEncoder = stiltMotor.getEncoder();

    CANSparkMax armMotor = new CANSparkMax(RobotMap.ClimbArm.ARM_MOTOR, MotorType.kBrushless);
    CANPIDController armPID = armMotor.getPIDController();
    CANEncoder armEncoder = armMotor.getEncoder();
    
    VictorSPX wheels = new VictorSPX(RobotMap.ClimbStilt.ROLL_WHEELS);
    
    public Climb() {
        super("Climb", -0.1, 0, 0);
        wheels.configFactoryDefault();
        wheels.setNeutralMode(NeutralMode.Brake);
        armMotor.setIdleMode(IdleMode.kBrake);
        armMotor.setSmartCurrentLimit(40);
        armMotor.burnFlash();
        // set PID coefficients
        armPID.setP(RobotMap.ClimbArm.kP);
        armPID.setI(RobotMap.ClimbArm.kI);
        armPID.setD(RobotMap.ClimbArm.kD);
        armPID.setIZone(RobotMap.ClimbArm.kIz);
        armPID.setFF(RobotMap.ClimbArm.kFF);
        armPID.setOutputRange(RobotMap.ClimbArm.kMinOutput, RobotMap.ClimbArm.kMaxOutput);
        armPID.setSmartMotionMaxVelocity(RobotMap.ClimbArm.maxVel, 0);
        armPID.setSmartMotionMinOutputVelocity(RobotMap.ClimbArm.minVel, 0);
        armPID.setSmartMotionMaxAccel(RobotMap.ClimbArm.maxAcc, 0);
        armPID.setSmartMotionAllowedClosedLoopError(RobotMap.ClimbArm.allowedErr, 0);
        
        stiltMotor.setIdleMode(IdleMode.kBrake);
        stiltMotor.setSmartCurrentLimit(40);
        stiltMotor.burnFlash();
        // set PID coefficients
        stiltPID.setP(RobotMap.ClimbStilt.kP);
        stiltPID.setI(RobotMap.ClimbStilt.kI);
        stiltPID.setD(RobotMap.ClimbStilt.kD);
        stiltPID.setIZone(RobotMap.ClimbStilt.kIz);
        stiltPID.setFF(RobotMap.ClimbStilt.kFF);
        stiltPID.setOutputRange(RobotMap.ClimbStilt.kMinOutput, RobotMap.ClimbStilt.kMaxOutput);
        stiltPID.setSmartMotionMaxVelocity(RobotMap.ClimbStilt.maxVel, 0);
        stiltPID.setSmartMotionMinOutputVelocity(RobotMap.ClimbStilt.minVel, 0);
        stiltPID.setSmartMotionMaxAccel(RobotMap.ClimbStilt.maxAcc, 0);
        stiltPID.setSmartMotionAllowedClosedLoopError(RobotMap.ClimbStilt.allowedErr, 0);
    }


    @Override
    public void initDefaultCommand() {
    }

    public void prepareLevel2() {
        armPID.setReference(RobotMap.ClimbArm.ARM_LEVEL2, ControlType.kSmartMotion);
    }

    public void prepareLevel3() {
        armPID.setReference(RobotMap.ClimbArm.ARM_LEVEL2, ControlType.kSmartMotion);
    }

    public void lift() {
        setSetpoint(-5);
        enable();
    }

    public void stopArm() {
        disable();
        armMotor.set(0);
    }

    public void level2(){
        stiltPID.setReference(RobotMap.ClimbStilt.STILT_LEVEL2, ControlType.kSmartMotion);
    }

    public void level3(){
        stiltPID.setReference(RobotMap.ClimbStilt.STILT_LEVEL3, ControlType.kSmartMotion);
    }

    public void retract() {
        stiltPID.setReference(0, ControlType.kSmartMotion);
    }

    public void runWheels(double value) {
        if (Math.abs(value)>0.25) wheels.set(ControlMode.PercentOutput, -value);
    }
    
    public void stopWheels() {
        wheels.set(ControlMode.PercentOutput, 0);
    }

    public void smartDash() {
        SmartDashboard.putNumber("stilt Encoder", stiltEncoder.getPosition());
        SmartDashboard.putNumber("arm Encoder", armEncoder.getPosition());
        SmartDashboard.putNumber("arm Output", armMotor.getAppliedOutput());
        SmartDashboard.putNumber("arm Output", armMotor.getAppliedOutput());
    }
    @Override
    protected double returnPIDInput() {
        return Robot.navxGyro.getTilt();
    }

    @Override
    protected void usePIDOutput(double output) {
        armMotor.set(output);
    }
}