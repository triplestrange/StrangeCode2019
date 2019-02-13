package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.commands.auto.PathTesting;
import frc.robot.profiling.PathFollower;
import frc.robot.profiling.PathTracking;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Robot extends TimedRobot {
    public static Joystick joy1, joy2;
    public static OI m_oi;
    public static SwerveDrive swerve;
    public static Hatch hatch;
    public static Gyro navxGyro;
    public static PathTracking path;
    public static PathFollower follower;
    public static Command autoCommand;
    // public static boolean Tape;
    // public static PowerDistributionPanel pdp;

    public void robotInit() {
        navxGyro = new Gyro();
        swerve = new SwerveDrive(navxGyro);
        hatch = new Hatch();
        joy1 = new Joystick(0);
        path = new PathTracking(swerve, navxGyro);
        follower = new PathFollower();
        m_oi = new OI();
        autoCommand = new PathTesting();
        // pdp = new PowerDistributionPanel();
        // Tape = true;
    }

    @Override
    public void robotPeriodic() {
        path.update();
        swerve.smartDash();
        Scheduler.getInstance().run();
        // SmartDashboard.putBoolean("Tape", Tape);
        // SmartDashboard.putNumber("Motor 0", pdp.getCurrent(0));
        // SmartDashboard.putNumber("Motor 1", pdp.getCurrent(1));
        // SmartDashboard.putNumber("Motor 2", pdp.getCurrent(14));
        // SmartDashboard.putNumber("Motor 3", pdp.getCurrent(15));
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
