package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchPistonPlace extends Command {
    public HatchPistonPlace() {
    }

    @Override
    protected void initialize() {
        Robot.hatch.out();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
        Robot.hatch.stop();
    }
}