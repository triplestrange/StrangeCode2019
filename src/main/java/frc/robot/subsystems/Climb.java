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

public class Climb extends PIDSubsystem{

    TalonSRX climb = new TalonSRX(RobotMap.Climb.ARM_MOTOR);
    VictorSPX wheels = new VictorSPX(RobotMap.Climb.ROLL_WHEELS);
    public DoubleSolenoid climbThings = new DoubleSolenoid(RobotMap.Climb.CLIMB_EXTEND, RobotMap.Climb.CLIMB_RETRACT);
    
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

    }


    @Override
    public void initDefaultCommand() {
    }

    public void prepareClimb() {
        climb.set(ControlMode.MotionMagic, RobotMap.Climb.PREPARE_CLIMB);
    }

    public void lift() {
        climbThings.set(Value.kForward);
        setSetpoint(-8);
        enable();
    }

    public void retract() {
        disable();
        wheels.set(ControlMode.PercentOutput, 0);
        climbThings.set(Value.kReverse);
    }

    public void RunWheels() {
        wheels.set(ControlMode.PercentOutput, -75);
    }
    
    public void stop() {
        wheels.set(ControlMode.PercentOutput, 0);
    }
    public void move() {
        SmartDashboard.putNumber("ClimbEncoder", climb.getSelectedSensorPosition());
        if (OI.joy1.getRawButton(RobotMap.Controller.X)) {
            climbThings.set(Value.kForward);
        }
        if (OI.joy1.getRawButton(RobotMap.Controller.Y)) {
            climbThings.set(Value.kReverse);
        }
        if (OI.joy1.getPOV() == RobotMap.Controller.UP) {
            climb.set(ControlMode.PercentOutput, -75);
        }
        else if (OI.joy1.getPOV() == RobotMap.Controller.DOWN) {
            climb.set(ControlMode.PercentOutput, 75);
        }
        else {
            climb.set(ControlMode.PercentOutput, 0);
        }
        
        if (OI.joy1.getRawButton(RobotMap.Controller.B)) {
            wheels.set(ControlMode.PercentOutput, -100);
        }
        else {
            wheels.set(ControlMode.PercentOutput, 0);
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