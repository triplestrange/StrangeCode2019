package frc.robot.commands.auto;

import frc.robot.commands.elevator.ElevatorHatch1;
import frc.robot.commands.hatch.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoNothing extends CommandGroup {
    public DoNothing() {
        addParallel(new HatchPistonGrab());
        addSequential(new HatchPistonExtend());
        addSequential(new ElevatorHatch1());
    }
}
