package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.commands.auto.PathTesting;
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
    public static PathTracking path;
    public static PathFollower follower;
    public static Command autoCommand;
    public static OI m_oi;
    public static NetworkTableInstance instance;
    public static NetworkTable visionNT;
    public static NetworkTableEntry yawRaw, yawDetected;
    public static double yaw;
    public static boolean tape;
    public static Spark led;
    public static Climb climb;
    public static Compressor succ;

    public void robotInit() {
        navxGyro = new Gyro();
        swerve = new SwerveDrive();
        hatch = new Hatch();
        elevator = new Elevator();
        path = new PathTracking();
        follower = new PathFollower();
        autoCommand = new PathTesting();
        cargo = new Cargo();
        led = new Spark(0);
        climb = new Climb();
        succ = new Compressor();
        m_oi = new OI();
    }

    @Override
    public void robotPeriodic() {
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
        }
        else if (hatch.hatchExtended && !hatch.hatchOpen) {
            led.set(-0.25);
        }
        else if (hatch.hatchOpen) {
            led.set(-0.21);
        }
        else {
            led.set(-0.45);
        }
climb.move();
    }

    @Override
    public void autonomousInit() {
        Robot.elevator.elevator1.setSelectedSensorPosition(RobotMap.Elevator.START_POSITION, 0, RobotMap.DEFAULT_TIMEOUT);
        succ.setClosedLoopControl(false);
        succ.stop();
        navxGyro.reset();
        autoCommand.start();
        swerve.setCoast();
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        succ.setClosedLoopControl(true);
        swerve.setBrake();
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testPeriodic() {
    }
}
