package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbRetract extends Command {
    public ClimbRetract() {
        requires(Robot.climb);
    }

    @Override
    protected void initialize() {
        Robot.climb.retract();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
