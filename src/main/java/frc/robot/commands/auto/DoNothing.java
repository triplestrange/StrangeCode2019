package frc.robot.commands.auto;

import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorMM;
import frc.robot.commands.hatch.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoNothing extends CommandGroup {
    public DoNothing() {
        addParallel(new HatchPistonGrab());
        addSequential(new HatchPistonExtend());
        addSequential(new ElevatorMM(RobotMap.Elevator.CARGO_SHIP));
    }
}
