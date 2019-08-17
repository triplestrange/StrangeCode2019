package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FinishClimb extends CommandGroup {
    public FinishClimb() {
        addSequential(new ArmStop());
        addSequential(new ClimbRetract());
  }
}
