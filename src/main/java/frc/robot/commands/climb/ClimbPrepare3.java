package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.*;

public class ClimbPrepare3 extends CommandGroup {

  public ClimbPrepare3() {
        addSequential(new ElevatorCargo2());
        addSequential(new ClimbArmLevel3());
  }
}
