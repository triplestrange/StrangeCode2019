package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;


public class ClimbStilt extends PIDSubsystem{

    CANSparkMax stiltMotor = new CANSparkMax(RobotMap.ClimbStilt.STILT_MOTOR, MotorType.kBrushless);
    CANEncoder stiltEncoder = stiltMotor.getEncoder();
    VictorSPX wheels = new VictorSPX(RobotMap.ClimbStilt.ROLL_WHEELS);
    
    public ClimbStilt() {
        super("ClimbStilt", 0.2, 0, 0.02);
        wheels.configFactoryDefault();
        wheels.setNeutralMode(NeutralMode.Brake);

        stiltMotor.setIdleMode(IdleMode.kCoast);
        stiltMotor.setSmartCurrentLimit(40);
        stiltMotor.burnFlash();
    }


    @Override
    public void initDefaultCommand() {
    }

    public void level2() {
        setSetpoint(RobotMap.ClimbStilt.STILT_LEVEL2);
        enable();
    }

    public void level3(){
        setSetpoint(RobotMap.ClimbStilt.STILT_LEVEL3);
        enable();
    }

    public void retract() {
        setSetpoint(5);
        enable();
    }

    public void runWheels(double value) {
        if (Math.abs(value) > 0.25)
            wheels.set(ControlMode.PercentOutput, -value);
        else stopWheels();
    }
    
    public void stopWheels() {
        wheels.set(ControlMode.PercentOutput, 0);
    }

    public void smartDash() {
        SmartDashboard.putNumber("stilt Encoder", stiltEncoder.getPosition());
        SmartDashboard.putNumber("stilt Output", stiltMotor.getAppliedOutput());
    }
    @Override
    protected double returnPIDInput() {
        return stiltEncoder.getPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        stiltMotor.set(output);
    }
}