package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.*;

public class ClimbPrepare2 extends CommandGroup {

  public ClimbPrepare2() {
        addSequential(new ElevatorMM(RobotMap.Elevator.CARGO_2ROCKET));
        addSequential(new WaitCommand(1));
        addSequential(new ClimbArmLevel2());
        addSequential(new WaitCommand(1.5));
        addSequential(new ElevatorMM(RobotMap.Elevator.CARGO_1ROCKET));
  }
}