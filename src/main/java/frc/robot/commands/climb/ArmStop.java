package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmStop extends Command {
    public ArmStop() {
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        Robot.arm.stopArm();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
