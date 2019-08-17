package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmBalance extends Command {
    public ArmBalance() {
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        Robot.arm.lift();
    }
    @Override
    protected boolean isFinished() {
        return true;
    }
}
