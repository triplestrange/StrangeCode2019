package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hatch extends Subsystem {
    public CANSparkMax hMotor;
    public CANEncoder encoder;
    Solenoid intakePiston = new Solenoid(RobotMap.Hatch.INTAKE_PISTON);
    public boolean hatchExtended, hatchOpen;
    public Hatch() {
        hMotor = new CANSparkMax(RobotMap.Hatch.hatchMotor, MotorType.kBrushless);
        encoder = hMotor.getEncoder();
        hMotor.setIdleMode(IdleMode.kBrake);
        hMotor.setSmartCurrentLimit(40);
        hMotor.burnFlash();
    }

    public void extend() {
        intakePiston.set(true);
    }
    
    public void retract() {
        intakePiston.set(false);
    }

    public void in() {
        hMotor.set(-0.4);
    }
    
    public void out() {
        hMotor.set(1);
    }
    public void stop() {
        hMotor.set(0);
    }
    @Override
    protected void initDefaultCommand() {
        hMotor.set(0);
    }
}
