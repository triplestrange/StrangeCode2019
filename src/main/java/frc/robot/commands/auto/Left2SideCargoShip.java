package frc.robot.commands.auto;

import frc.robot.profiling.SwerveWaypoint;
import frc.robot.commands.swerve.*;
import frc.robot.commands.elevator.ElevatorHatch1;
import frc.robot.commands.hatch.*;
import jaci.pathfinder.Pathfinder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Left2SideCargoShip extends CommandGroup {
    public Left2SideCargoShip() {
        addParallel(new HatchPistonGrab());
        addParallel(new HatchPistonExtend());
        addParallel(new ElevatorHatch1());
        addSequential(new PathCommand(-201, 68,
            new SwerveWaypoint(-201, 68, Pathfinder.d2r(90), 270),
            new SwerveWaypoint(-201, 116, Pathfinder.d2r(90), 270),
            new SwerveWaypoint(-242, 164, Pathfinder.d2r(90), 360),
            new SwerveWaypoint(-224, 250, Pathfinder.d2r(70), 360)
        ));
        addSequential(new SwerveDriveVisionForwardsAuto(3.5, 15));
        addSequential(new HatchPistonPlace());
        addSequential(new SwerveDriveVisionReverseAuto(0.75, 25));
        addSequential(new PathCommand(-225, 250,
            new SwerveWaypoint(-225, 250, Pathfinder.d2r(250), 360),
            new SwerveWaypoint(-280, 100, Pathfinder.d2r(270), 90),
            new SwerveWaypoint(-280, 70, Pathfinder.d2r(270), 90)
        ));
        addSequential(new SwerveDriveVisionForwardsAuto(0.75, 35));
        addSequential(new HatchPistonGrab());
        addSequential(new PathCommand(-280, 13, 
            new SwerveWaypoint(-280, 13, Pathfinder.d2r(90), 90),
            new SwerveWaypoint(-280, 61, Pathfinder.d2r(90), 90),
            new SwerveWaypoint(-233, 150, Pathfinder.d2r(70), 0),
            new SwerveWaypoint(-225, 269, Pathfinder.d2r(90), 0)));
        addSequential(new SwerveDriveVisionForwardsAuto(2, 15));
        addSequential(new HatchPistonPlace());
        addSequential(new SwerveDriveVisionReverseAuto(1, 15));
        // addSequential(new PathCommand(-280, 250, 
        // new SwerveWaypoint(-280, 250, Pathfinder.d2r(0), 116),
        // new SwerveWaypoint(-200, 256, Pathfinder.d2r(0), 90)));
    }
}
