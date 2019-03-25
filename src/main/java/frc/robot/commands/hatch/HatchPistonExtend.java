package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPistonExtend extends Command {
    public HatchPistonExtend() {
    }

    @Override
    protected void initialize() {
        if (Robot.elevator.clearForHatch())
            Robot.hatch.extend();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        if (Robot.elevator.clearForHatch())
            Robot.hatch.retract();
    }

    @Override
    protected void interrupted() {
        end();
    }
}