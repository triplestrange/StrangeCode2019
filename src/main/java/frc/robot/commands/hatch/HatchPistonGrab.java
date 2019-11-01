package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPistonGrab extends Command {
    public HatchPistonGrab() {
    }

    @Override
    protected void initialize() {
        Robot.hatch.in();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.hatch.stop();
    }
}
