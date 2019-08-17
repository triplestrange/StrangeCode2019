package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbStilts2 extends Command {
    public ClimbStilts2() {
        requires(Robot.stilt);
    }

    @Override
    protected void initialize() {
        Robot.stilt.level2();  
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
