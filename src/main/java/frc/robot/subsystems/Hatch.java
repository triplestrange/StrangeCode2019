package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hatch extends Subsystem {

    Solenoid intakePiston = new Solenoid(RobotMap.Hatch.INTAKE_PISTON);
    DoubleSolenoid placingPiston = new DoubleSolenoid(RobotMap.Hatch.EXTEND, RobotMap.Hatch.RETRACT);
    public boolean hatchExtended, hatchOpen;

    public Hatch() {
        super();
    }

    public void grab() {
        intakePiston.set(true);
        hatchOpen = true;
    }
    
    public void place() {
        intakePiston.set(false);
        hatchOpen = false;
    }

    public void extend() {
        placingPiston.set(Value.kForward);
        hatchExtended = true;
    }

    public void retract() {
        placingPiston.set(Value.kReverse);
        hatchExtended = false;
    }

    @Override
    protected void initDefaultCommand() {
        //yeet take that 5190
    }
}
