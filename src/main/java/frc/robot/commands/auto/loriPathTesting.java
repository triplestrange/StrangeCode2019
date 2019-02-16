package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.*;
import frc.robot.commands.swerve.SwerveRotate;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class loriPathTesting extends CommandGroup {
    public loriPathTesting() {
        // goes to  first cargo ship panel holder thingy
        addSequential(new PathCommand(
            new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
            new SwerveWaypoint(20, 100, Pathfinder.d2r(90), 0),
            new SwerveWaypoint(20, 200, Pathfinder.d2r(90), -90)
        ));
        addSequential(new PathCommand(
            new SwerveWaypoint(0, 0, Pathfinder.d2r(90), 0),
            new SwerveWaypoint(20, 100, Pathfinder.d2r(90), 0),
            new SwerveWaypoint(20, 200, Pathfinder.d2r(90), -90)
        ));
    }
}