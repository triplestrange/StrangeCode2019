package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbArmLevel3 extends Command {
    public ClimbArmLevel3() {
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        Robot.arm.prepareLevel3();
    }
    
    @Override
    protected void execute() {
        Robot.arm.prepareLevel3();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    protected void end() {}
}
