package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SwerveSetLowSpeed extends Command {
    public SwerveSetLowSpeed() {
    }

    @Override
    public void execute() {
        Robot.swerve.setSpeed(15, 15);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.swerve.setSpeed(RobotMap.SwerveDrive.SPEED, RobotMap.SwerveDrive.TURN_RATE);
    }
}