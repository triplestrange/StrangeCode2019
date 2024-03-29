package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPistonExtend extends Command {
    public HatchPistonExtend() {
    }

    @Override
    protected void initialize() {
        Robot.hatch.extend();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }
}