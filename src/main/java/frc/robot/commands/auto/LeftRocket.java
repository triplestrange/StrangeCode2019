package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.swerve.*;
import frc.robot.commands.hatch.HatchPistonIn;
import frc.robot.commands.hatch.HatchPistonOut;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftRocket extends CommandGroup {
    public LeftRocket() {
        addSequential(new PathCommand(-201, 68,
            new SwerveWaypoint(-201, 68, Pathfinder.d2r(90), 270),
            new SwerveWaypoint(-201, 110, Pathfinder.d2r(90), 270),
            new SwerveWaypoint(-270, 150, Pathfinder.d2r(120), 240)
        ));
        addSequential(new SwerveDriveVisionForwardsAuto(1.5, 20));
        addSequential(new HatchPistonOut());
        addSequential(new SwerveDriveVisionReverseAuto(1, 15));
        addSequential(new PathCommand(-280, 175,
        new SwerveWaypoint(-280, 175, Pathfinder.d2r(300), 240),
        new SwerveWaypoint(-275, 100, Pathfinder.d2r(270), 90),
        new SwerveWaypoint(-275, 60, Pathfinder.d2r(270), 90)
        ));
        addSequential(new SwerveDriveVisionForwardsAuto(1, 30));
        addSequential(new HatchPistonIn());
        addSequential(new PathCommand(-260, 22, 
            new SwerveWaypoint(-260, 22, Pathfinder.d2r(90), 90),
                new SwerveWaypoint(-260, 102, Pathfinder.d2r(90), 90),
                new SwerveWaypoint(-236, 227, Pathfinder.d2r(90), 120),
                new SwerveWaypoint(-252, 245, Pathfinder.d2r(150), 120),
                new SwerveWaypoint(-280, 250, Pathfinder.d2r(180), 120)));
        addSequential(new SwerveDriveVisionForwardsAuto(2, 15));
        addSequential(new HatchPistonOut());
        addSequential(new SwerveDriveVisionReverseAuto(1, 15));
        addSequential(new PathCommand(-280, 250, 
        new SwerveWaypoint(-280, 250, Pathfinder.d2r(0), 116),
        new SwerveWaypoint(-200, 256, Pathfinder.d2r(0), 90)));
    }
}
