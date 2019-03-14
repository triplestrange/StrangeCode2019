package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class SwerveSetHighSpeed extends Command {
    public SwerveSetHighSpeed() {
    }

    @Override
    public void execute() {
        Robot.swerve.setSpeed(100, 100);
        OI.joy1.setRumble(RumbleType.kRightRumble, 1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.swerve.setSpeed(75, 75);
    }
}