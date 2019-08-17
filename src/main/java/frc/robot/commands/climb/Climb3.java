package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Climb3 extends CommandGroup {
    public Climb3() {
        addParallel(new ArmBalance());
        addSequential(new ClimbStilts3());
  }
}
