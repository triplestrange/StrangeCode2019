package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPistonGrab extends Command {
    public HatchPistonGrab() {
        requires(Robot.hatch);
    }

    @Override
    protected void initialize() {
        Robot.hatch.place();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
