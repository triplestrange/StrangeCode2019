package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.*;
import frc.robot.commands.swerve.SwerveRotate;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathTesting extends CommandGroup {
    public PathTesting() {
        addSequential(new PathCommand(
            new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0), 
            new SwerveWaypoint(6, 36, Pathfinder.d2r(80), 0),
            new SwerveWaypoint(90, 138, Pathfinder.d2r(60), 25)
        ));
        addSequential(new PathCommand(
            new SwerveWaypoint(0, 0, Pathfinder.d2r(270), 25),
            new SwerveWaypoint(0, -180, Pathfinder.d2r(270), 180)
        ));
        addSequential(new PathCommand(
            new SwerveWaypoint(0, 0, Pathfinder.d2r(270), 180),
            new SwerveWaypoint(0, 180, Pathfinder.d2r(270), 25)
        ));
    }
}
