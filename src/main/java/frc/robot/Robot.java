package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.commands.auto.PathTesting;
import frc.robot.profiling.PathFollower;
import frc.robot.profiling.PathTracking;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;

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
    public static NetworkTableEntry yawRaw;
    public static double yaw;


    public void robotInit() {
        yaw = 0;
        instance = NetworkTableInstance.getDefault();
        visionNT = instance.getTable("ChickenVision");
        yawRaw = visionNT.getEntry("tapeYaw");
        navxGyro = new Gyro();
        swerve = new SwerveDrive(navxGyro);
        hatch = new Hatch();
        elevator = new Elevator();
        path = new PathTracking(swerve, navxGyro);
        follower = new PathFollower();
        m_oi = new OI();
        autoCommand = new PathTesting();
        cargo = new Cargo();
    }

    @Override
    public void robotPeriodic() {
        yaw = yawRaw.getDouble(0);
        path.update();
        swerve.smartDash();
        hatch.smartdash();
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        path.reset();
        autoCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        swerve.smartDash();
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
    }
}
