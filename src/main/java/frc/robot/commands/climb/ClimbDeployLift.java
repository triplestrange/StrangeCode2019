package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbDeployLift extends Command {
    public ClimbDeployLift() {
        requires(Robot.climb);
    }

    @Override
    protected void initialize() {
        Robot.climb.lift();  
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
