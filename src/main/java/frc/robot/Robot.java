package frc.robot;

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

    public void robotInit() {
        // yaw = 0;
        navxGyro = new Gyro();
        swerve = new SwerveDrive(navxGyro);
        hatch = new Hatch();
        elevator = new Elevator();
        // path = new PathTracking(swerve, navxGyro);
        // follower = new PathFollower();
        m_oi = new OI();
        // autoCommand = new PathTesting();
        cargo = new Cargo();
        led = new Spark(0);
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
        // path.update();
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
    }

    @Override
    public void autonomousInit() {
        navxGyro.reset();
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
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
