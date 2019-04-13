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


public class Climb extends PIDSubsystem{
//how about now?
    CANSparkMax stiltMotor = new CANSparkMax(30, MotorType.kBrushless); 
    
    TalonSRX climb = new TalonSRX(RobotMap.Climb.ARM_MOTOR);
    VictorSPX wheels = new VictorSPX(RobotMap.Climb.ROLL_WHEELS);
    
    public Climb() {
        super("Climb", -0.1, 0, 0);
        climb.configFactoryDefault();
        wheels.configFactoryDefault();
        climb.setNeutralMode(NeutralMode.Brake);
        wheels.setNeutralMode(NeutralMode.Brake);
        climb.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.DEFAULT_TIMEOUT);
        climb.setSelectedSensorPosition(0, 0, RobotMap.DEFAULT_TIMEOUT);
        climb.setSensorPhase(true);
        climb.setInverted(true);
        climb.selectProfileSlot(0, 0);
        climb.config_kF(0, 1023.0 / 39875, RobotMap.DEFAULT_TIMEOUT);
        climb.config_kP(0, 0.1, RobotMap.DEFAULT_TIMEOUT);
        climb.config_kI(0, 0, RobotMap.DEFAULT_TIMEOUT);
        climb.config_kD(0, 0, RobotMap.DEFAULT_TIMEOUT);
        climb.configMotionCruiseVelocity(39875, RobotMap.DEFAULT_TIMEOUT);
        climb.configMotionAcceleration(39875/2, RobotMap.DEFAULT_TIMEOUT);
        stiltMotor.setIdleMode(IdleMode.kBrake);
        stiltMotor.setSmartCurrentLimit(40);
        // stiltMotor.setOpenLoopRampRate(1);
        stiltMotor.setParameter(ConfigParameter.kCtrlType, ControlType.kDutyCycle.value);
        stiltMotor.burnFlash();

    }


    @Override
    public void initDefaultCommand() {
    }

    public void prepareClimb() {
        climb.set(ControlMode.MotionMagic, RobotMap.Climb.PREPARE_CLIMB);
    }

    public void lift() {
        setSetpoint(-8);
        enable();
    }

    public void retract() {
        disable();
        wheels.set(ControlMode.PercentOutput, 0);
        
    }

    public void RunWheels() {
        wheels.set(ControlMode.PercentOutput, -75);
    }
    
    public void stop() {
        wheels.set(ControlMode.PercentOutput, 0);
    }

    public void move() {
        if (Math.abs(OI.joy4.getRawAxis(RobotMap.Controller.LY)) > 0.1) {
            stiltMotor.set(OI.joy4.getRawAxis(RobotMap.Controller.LY));
        }
        if (Math.abs(OI.joy4.getRawAxis(RobotMap.Controller.RY)) > 0.1) {
            climb.set(ControlMode.PercentOutput,OI.joy4.getRawAxis(RobotMap.Controller.RY));
        }
        if (Math.abs(OI.joy4.getRawAxis(RobotMap.Controller.LT)) > 0.1) {
            wheels.set(ControlMode.PercentOutput,OI.joy4.getRawAxis(RobotMap.Controller.LT));
        }
        if (Math.abs(OI.joy4.getRawAxis(RobotMap.Controller.RT)) > 0.1) {
            wheels.set(ControlMode.PercentOutput, OI.joy4.getRawAxis(RobotMap.Controller.RT) * (-1));
        }
    }

    public void smartDash() {
        SmartDashboard.putNumber("Climb Encoder", climb.getSelectedSensorPosition());
    }
    @Override
    protected double returnPIDInput() {
        return Robot.navxGyro.getTilt();
    }

    @Override
    protected void usePIDOutput(double output) {
        climb.set(ControlMode.PercentOutput, output);
        SmartDashboard.putNumber("Encoder_output", output);
    }
}