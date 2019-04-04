package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SwerveDriveVisionForwards extends Command {
    public SwerveDriveVisionForwards() {
        requires(Robot.swerve);
    }

    @Override
    public void execute() {
        Robot.swerve.driveWithVision(Robot.yaw, true);
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