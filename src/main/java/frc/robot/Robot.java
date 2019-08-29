package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
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
    public static ClimbArm arm;
    public static ClimbStilt stilt;
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
        arm = new ClimbArm();
        stilt = new ClimbStilt();
        succ = new Compressor();
        m_oi = new OI();
        autoChooser = new SendableChooser<Command>();
        autoChooser.setDefaultOption("Driver Control", new DoNothing());
        autoChooser.addOption("Left 2 Rocket", new Left2Rocket());
        autoChooser.addOption("FL 2 Side Cargo", new Left2SideCargoShip());
        autoChooser.addOption("Left 2 Front Cargo Back Rocket", new FLCargoBackRocket());
        autoChooser.addOption("Right 2 Rocket", new Right2Rocket());
        autoChooser.addOption("Right 2 Rocket", new Left2SideCargoShip());
        SmartDashboard.putData("Autonomous Chooser", autoChooser);
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
        visionNT = NetworkTableInstance.getDefault().getTable("ChickenVision");
        yawDetected = visionNT.getEntry("tapeDetected");
        tape = yawDetected.getBoolean(false);
        yawRaw = visionNT.getEntry("tapeYaw");
        yaw = yawRaw.getDouble(0);
        SmartDashboard.putNumber("yawval", yaw);
        SmartDashboard.putBoolean("tapeval", tape);
        swerve.smartDash();
        elevator.smartDash();
        arm.smartDash();
        stilt.smartDash();
        path.update();
        if (hatch.hatchExtended && hatch.hatchOpen) {
            led.set(-0.23);
        } else if (hatch.hatchExtended && !hatch.hatchOpen) {
            led.set(-0.25);
        } else if (hatch.hatchOpen) {
            led.set(-0.21);
        } else {
            led.set(-0.45);
        }
    }

    @Override
    public void autonomousInit() {
        elevator.resetEncoder(RobotMap.Elevator.START_POSITION);
        succ.setClosedLoopControl(false);
        succ.stop();
        navxGyro.reset();
        autoCommand = (Command) autoChooser.getSelected();
        if (autoCommand != null) {
            autoCommand.cancel();
            autoCommand.start();
        }
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
    public void disabledInit() {
        swerve.setCoast();
        if (autoCommand != null) {
            autoCommand.cancel();
        }
    }
}