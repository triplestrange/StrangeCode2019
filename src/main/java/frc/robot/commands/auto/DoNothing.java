package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.swerve.*;
import frc.robot.commands.elevator.ElevatorHatch1;
import frc.robot.commands.hatch.*;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoNothing extends CommandGroup {
    public DoNothing() {
        addParallel(new HatchPistonGrab());
        addParallel(new HatchPistonExtend());
        addParallel(new ElevatorHatch1());
    }
}
