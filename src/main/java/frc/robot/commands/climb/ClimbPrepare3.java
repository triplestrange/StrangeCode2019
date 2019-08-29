package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.*;

public class ClimbPrepare3 extends CommandGroup {

  public ClimbPrepare3() {
        addSequential(new ElevatorMM(RobotMap.Elevator.CARGO_2ROCKET));
        addSequential(new WaitCommand(1));
        addSequential(new ClimbArmLevel3());
        addSequential(new WaitCommand(1.5));
        addSequential(new ElevatorMM(RobotMap.Elevator.CARGO_1ROCKET));
  }
}
