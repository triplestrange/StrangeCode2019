package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbArmLevel3 extends Command {
    public ClimbArmLevel3() {
        requires(Robot.climb);
    }

    @Override
    protected void initialize() {
        Robot.climb.prepareLevel3();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
    protected void end() {}
}
