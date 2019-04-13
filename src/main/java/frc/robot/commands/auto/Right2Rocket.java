package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.swerve.*;
import frc.robot.commands.elevator.ElevatorHatch1;
import frc.robot.commands.hatch.*;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Right2Rocket extends CommandGroup {
    public Right2Rocket() {
        addParallel(new HatchPistonGrab());
        addParallel(new HatchPistonExtend());
        addParallel(new ElevatorHatch1());
        addSequential(new PathCommand(-110, 68, 
                new SwerveWaypoint(-110, 68, Pathfinder.d2r(90), 270),
                new SwerveWaypoint(-110, 110, Pathfinder.d2r(90), 270),
                new SwerveWaypoint(-55, 150, Pathfinder.d2r(60), 300)), 3.25);
        addSequential(new SwerveDriveVisionForwardsAuto(2.5, 20));
        addSequential(new HatchPistonPlace());
        addSequential(new SwerveDriveVisionReverseAuto(1, 15));
        //     addSequential(new PathCommand(-280, 175,
        //     new SwerveWaypoint(-280, 175, Pathfinder.d2r(300), 240),
        //     new SwerveWaypoint(-280, 100, Pathfinder.d2r(270), 90),
        //     new SwerveWaypoint(-280, 60, Pathfinder.d2r(270), 90)
        //     ), 3.25);
        //     addSequential(new SwerveDriveVisionForwardsAuto(1.5, 25));
        //     addSequential(new HatchPistonGrab());
        //     addSequential(new PathCommand(-280, 22, 
        //         new SwerveWaypoint(-280, 22, Pathfinder.d2r(90), 90),
        //             new SwerveWaypoint(-280, 102, Pathfinder.d2r(90), 90),
        //             new SwerveWaypoint(-236, 227, Pathfinder.d2r(90), 120),
        //             new SwerveWaypoint(-252, 245, Pathfinder.d2r(150), 120),
        //             new SwerveWaypoint(-280, 250, Pathfinder.d2r(180), 120)));
        //     addSequential(new SwerveDriveVisionForwardsAuto(2, 15));
        //     addSequential(new HatchPistonPlace());
        //     addSequential(new SwerveDriveVisionReverseAuto(1, 15));
        //     addSequential(new PathCommand(-280, 250, 
        //     new SwerveWaypoint(-280, 250, Pathfinder.d2r(0), 116),
        //     new SwerveWaypoint(-200, 256, Pathfinder.d2r(0), 90)));
        // }
    }
}
