package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.*;

public class ClimbPrepare extends CommandGroup {

  public ClimbPrepare() {
        addSequential(new ElevatorCargo2());
        addSequential(new ClimbMoveArm());
  }
}
