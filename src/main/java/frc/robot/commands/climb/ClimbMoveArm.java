package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbMoveArm extends Command {
    public ClimbMoveArm() {
        requires(Robot.climb);
    }

    @Override
    protected void initialize() {
        Robot.climb.prepareClimb();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
    protected void end() {}
}
