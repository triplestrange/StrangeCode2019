package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
//This should import it...
import frc.robot.commands.auto.*;
import frc.robot.profiling.PathFollower;
import frc.robot.profiling.PathTracking;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {
    public static Hatch hatch;
    public static Cargo cargo;
    public static Elevator elevator;
    public static Gyro navxGyro;
    public static SwerveDrive swerve;
    public static Climb climb;
    public static Compressor succ;
    public static PathTracking path;
    public static PathFollower follower;
    public static OI m_oi;
    public static NetworkTableInstance instance;
    public static NetworkTable visionNT;
    public static NetworkTableEntry yawRaw, yawDetected;
    public static double yaw;
    public static boolean tape;
    public static Spark led;
    public static Command autoCommand;
    public static SendableChooser<Command> autoChooser;

    public void robotInit() {
        navxGyro = new Gyro();
        swerve = new SwerveDrive();
        hatch = new Hatch();
        elevator = new Elevator();
        path = new PathTracking();
        follower = new PathFollower();
        cargo = new Cargo();
        led = new Spark(0);
        climb = new Climb();
        succ = new Compressor();
        m_oi = new OI();
        autoChooser = new SendableChooser<Command>();
        // autoChooser.setDefaultOption("Do Nothing", new DoNothing());
        // autoChooser.addOption("Left 2 Rocket", new Left2Rocket());
        // autoChooser.addOption("FL 2 Side Cargo", new Left2SideCargoShip());
        // autoChooser.addOption("Left 2 Front Cargo Back Rocket", new FLCargoBackRocket());
        // autoChooser.addOption("Right 2 Rocket", new Right2Rocket());
        // SmartDashboard.putData("autoChooser", autoChooser);
        // autoChooser.addOption("Right 2 Rocket", new Left2SideCargoShip());
    }

    @Override
    public void robotPeriodic() {
        autoCommand = (Command) autoChooser.getSelected();
        SmartDashboard.putData("Selected Auto", autoCommand);
        visionNT = NetworkTableInstance.getDefault().getTable("ChickenVision");
        yawDetected = visionNT.getEntry("tapeDetected");
        tape = yawDetected.getBoolean(false);
        yawRaw = visionNT.getEntry("tapeYaw");
        yaw = yawRaw.getDouble(0);
        SmartDashboard.putNumber("yawval", yaw);
        SmartDashboard.putBoolean("tapeval", tape);
        path.update();
        swerve.smartDash();
        elevator.smartDash();
        Scheduler.getInstance().run();
        if (hatch.hatchExtended && hatch.hatchOpen) {
            led.set(-0.23);
        } else if (hatch.hatchExtended && !hatch.hatchOpen) {
            led.set(-0.25);
        } else if (hatch.hatchOpen) {
            led.set(-0.21);
        } else {
            led.set(-0.45);
        }
        climb.smartDash();
        climb.move();
    }

    @Override
    public void autonomousInit() {
        Robot.elevator.elevator1.setSelectedSensorPosition(RobotMap.Elevator.START_POSITION, 0, RobotMap.DEFAULT_TIMEOUT);
        succ.setClosedLoopControl(false);
        succ.stop();
        navxGyro.reset();
        if (autoCommand != null) {
            autoCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        succ.setClosedLoopControl(true);
        swerve.setBrake();
        if (autoCommand != null) {
        autoCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void disabledInit() {
        swerve.setCoast();
        if (autoCommand != null) {
            autoCommand.cancel();
        }
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testPeriodic() {
    }
}
