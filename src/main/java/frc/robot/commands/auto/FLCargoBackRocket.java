package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.swerve.*;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorMM;
import frc.robot.commands.hatch.*;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FLCargoBackRocket extends CommandGroup {
    public FLCargoBackRocket() {
        addParallel(new HatchPistonGrab());
        addParallel(new HatchPistonExtend());
        addParallel(new ElevatorMM(RobotMap.Elevator.HATCH_1ROCKET));
        addSequential(new PathCommand(-163, 68,
            new SwerveWaypoint(-163, 68, Pathfinder.d2r(90), 270),
            new SwerveWaypoint(-163, 120, Pathfinder.d2r(90), 270),
            new SwerveWaypoint(-167, 180, Pathfinder.d2r(90), 270)
        ));
        addSequential(new SwerveDriveVisionForwardsAuto(1, 15));
        addSequential(new HatchPistonPlace());
        addSequential(new SwerveDriveVisionReverseAuto(1, 15));
        addSequential(new PathCommand(-180, 180,
        new SwerveWaypoint(-280, 175, Pathfinder.d2r(270), 270),
        new SwerveWaypoint(-277, 100, Pathfinder.d2r(270), 90),
        new SwerveWaypoint(-280, 60, Pathfinder.d2r(270), 90)
        ));
        addSequential(new SwerveDriveVisionForwardsAuto(1.75, 20));
        addSequential(new HatchPistonGrab());
        addSequential(new PathCommand(-260, 22, 
            new SwerveWaypoint(-260, 22, Pathfinder.d2r(90), 90),
                new SwerveWaypoint(-260, 102, Pathfinder.d2r(90), 90),
                new SwerveWaypoint(-236, 227, Pathfinder.d2r(90), 120),
                new SwerveWaypoint(-252, 245, Pathfinder.d2r(150), 120),
                new SwerveWaypoint(-262, 262, Pathfinder.d2r(180), 120)));
        addSequential(new SwerveDriveVisionForwardsAuto(2, 15));
        addSequential(new HatchPistonPlace());
        addSequential(new SwerveDriveVisionReverseAuto(1, 15));
        // addSequential(new PathCommand(-280, 250, 
        // new SwerveWaypoint(-280, 250, Pathfinder.d2r(0), 116),
        // new SwerveWaypoint(-200, 256, Pathfinder.d2r(0), 90)));
    }
}
