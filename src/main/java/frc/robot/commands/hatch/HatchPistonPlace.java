package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPistonPlace extends Command {
    public HatchPistonPlace() {
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