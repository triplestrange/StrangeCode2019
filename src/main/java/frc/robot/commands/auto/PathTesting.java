package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.swerve.PathCommand;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PathTesting extends CommandGroup {
    public PathTesting() {
    // addSequential(new PathCommand(-120, 60,
    //          new SwerveWaypoint(-120, 60, Pathfinder.d2r(90), 0),
    //          new SwerveWaypoint(-120, 96, Pathfinder.d2r(90), 0),
    //          new SwerveWaypoint(-21, 200, Pathfinder.d2r(60), 25)
    //     ));
    //     addSequential(new PathCommand(-21, 234,
    //          new SwerveWaypoint(-21, 200, Pathfinder.d2r(60), 25),
    //          new SwerveWaypoint(-18, 24, Pathfinder.d2r(270), 180)
    //     ));
        addSequential(new PathCommand(-201, 68,
            //  new SwerveWaypoint(-96, 48, Pathfinder.d2r(90), 0),
            //  new SwerveWaypoint(-96, 84, Pathfinder.d2r(90), 0),
             new SwerveWaypoint(-201, 68, Pathfinder.d2r(0), 270),
             new SwerveWaypoint(-249, 68, Pathfinder.d2r(0), 270)
        ));
        // addSequential(new PathCommand(
        //     new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
        //     new SwerveWaypoint(0, 10, Pathfinder.d2r(90), 0)
        // ));
    }
}
