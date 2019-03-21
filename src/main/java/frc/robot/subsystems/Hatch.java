package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hatch extends Subsystem {

    Solenoid intakePiston = new Solenoid(RobotMap.Hatch.INTAKE_PISTON);
    DoubleSolenoid shortPiston = new DoubleSolenoid(RobotMap.Hatch.SHORT_EXTEND, RobotMap.Hatch.SHORT_RETRACT);
    DoubleSolenoid longPiston = new DoubleSolenoid(RobotMap.Hatch.LONG_EXTEND, RobotMap.Hatch.LONG_RETRACT);

    public Hatch() {
    }

    public void move() {
    }

    public void smartdash() {
    }

    public void grab() {
        intakePiston.set(true);
    }
    
    public void place() {
        intakePiston.set(false);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
