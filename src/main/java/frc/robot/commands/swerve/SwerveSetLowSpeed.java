package frc.robot.commands.swerve;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class SwerveSetLowSpeed extends Command {
    public SwerveSetLowSpeed() {
    }

    @Override
    public void execute() {
        Robot.swerve.setSpeed(15, 15);
        OI.joy1.setRumble(RumbleType.kLeftRumble, 1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.swerve.setSpeed(75, 75);
        OI.joy1.setRumble(RumbleType.kLeftRumble, 0);
    }
}