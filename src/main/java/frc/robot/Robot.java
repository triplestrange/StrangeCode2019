package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.profiling.PathFollower;
import frc.robot.profiling.PathTracking;

import edu.wpi.first.wpilibj.Joystick;

public class Robot extends TimedRobot {
    public static Joystick joy1, joy2;
    public static OI m_oi;
    public static SwerveDrive swerve;
    public static Gyro navxGyro;
    public static PathTracking path;
    public static PathFollower follower;

    public void robotInit() {
        m_oi = new OI();
        navxGyro = new Gyro();
        swerve = new SwerveDrive(navxGyro);
        joy1 = new Joystick(0);
        path = new PathTracking(swerve);
        follower = new PathFollower();
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
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
