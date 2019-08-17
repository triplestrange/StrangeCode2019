package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbArmLevel2 extends Command {
    public ClimbArmLevel2() {
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        Robot.arm.prepareLevel2();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
    protected void end() {}
}
