package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SwerveDriveVisionForwards extends Command {
    public SwerveDriveVisionForwards() {
        requires(Robot.swerve);
    }

    @Override
    public void execute() {
        Robot.swerve.driveWithVision(Robot.yaw, true);
        Robot.swerve.setSpeed(15, 100);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.swerve.driveNormal(0, 0, 0);
        Robot.swerve.setSpeed(RobotMap.SwerveDrive.SPEED, RobotMap.SwerveDrive.TURN_RATE);
    }
}