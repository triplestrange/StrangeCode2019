package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.profiling.PathFollower;
import frc.robot.profiling.PathTracking;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {
    public static Hatch hatch;
    public static Elevator elevator;
    public static Gyro navxGyro;
    public static SwerveDrive swerve;
    public static PathTracking path;
    public static PathFollower follower;
    public static OI m_oi;
    public static NetworkTableInstance instance;
    public static NetworkTable visionNT;
    public static NetworkTableEntry yawRaw, yawDetected;
    public static double yaw;
    public static boolean tape;
    public static Spark led;

    public void robotInit() {
        navxGyro = new Gyro();
        swerve = new SwerveDrive();
        hatch = new Hatch();
        elevator = new Elevator();
        path = new PathTracking();
        follower = new PathFollower();
        led = new Spark(0);
        m_oi = new OI();
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
        swerve.smartDash();
        elevator.smartDash();
        path.update();
    }

    @Override
    public void autonomousInit() {
        swerve.setBrake();
        elevator.resetEncoder(RobotMap.Elevator.START_POSITION);
        navxGyro.reset();
    }

    @Override
    public void teleopInit() {
        swerve.setBrake();
    }

    @Override
    public void disabledInit() {
        swerve.setCoast();
    }
}