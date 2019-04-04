package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SwerveDriveVisionForwardsAuto extends Command {
    double time;
    double speed;
    double startTime;

    public SwerveDriveVisionForwardsAuto(double time, double speed) {
        requires(Robot.swerve);
        this.time = time;
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        startTime = System.currentTimeMillis() / 1e3;
    }

    @Override
    public void execute() {
        Robot.swerve.autoDriveWithVision(Robot.yaw, true, speed);
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() / 1e3 - startTime >= time;
    }

    @Override
    public void end() {
        Robot.swerve.driveNormal(0, 0, 0);
    }
}