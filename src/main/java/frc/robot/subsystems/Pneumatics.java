package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {

    DoubleSolenoid rearPiston = new DoubleSolenoid(4, 3);
    DoubleSolenoid frontPiston = new DoubleSolenoid(6, 7);

    public Pneumatics() {
        super();
    }

    public void allIn() {
        frontPiston.set(DoubleSolenoid.Value.kForward);
        Timer.delay(2.0);
        rearPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void allOut() {
        rearPiston.set(DoubleSolenoid.Value.kReverse);
        frontPiston.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
