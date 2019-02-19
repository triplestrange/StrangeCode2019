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
        addSequential(new PathCommand(-96, 48,
            //  new SwerveWaypoint(-96, 48, Pathfinder.d2r(90), 0),
            //  new SwerveWaypoint(-96, 84, Pathfinder.d2r(90), 0),
             new SwerveWaypoint(-84, 175, Pathfinder.d2r(90), 0),
             new SwerveWaypoint(-24, 250, Pathfinder.d2r(135), -30)
        ));
        // addSequential(new PathCommand(
        //     new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
        //     new SwerveWaypoint(0, 10, Pathfinder.d2r(90), 0)
        // ));
    }
}
