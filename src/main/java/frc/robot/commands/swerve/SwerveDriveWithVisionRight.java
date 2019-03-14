package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SwerveDriveWithVisionRight extends Command {
    public SwerveDriveWithVisionRight() {
        requires(Robot.swerve);
    }

    @Override
    public void execute() {
        Robot.swerve.driveWithVision(Robot.yaw, 1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.swerve.driveNormal(0, 0, 0);
    }
}