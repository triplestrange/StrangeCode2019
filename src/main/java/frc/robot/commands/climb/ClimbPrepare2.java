package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.*;

public class ClimbPrepare2 extends CommandGroup {

  public ClimbPrepare2() {
        addSequential(new ElevatorCargo2());
        addSequential(new ClimbArmLevel2());
  }
}
