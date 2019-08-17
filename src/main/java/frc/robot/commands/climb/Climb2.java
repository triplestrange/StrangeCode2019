package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class Climb2 extends CommandGroup {
    public Climb2() {
        addParallel(new ArmBalance());
        addSequential(new ClimbStilts2());
  }
}
