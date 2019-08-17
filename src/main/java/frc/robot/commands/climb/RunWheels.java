package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class RunWheels extends Command {
    public RunWheels() {
    }

    @Override
    public void execute() {
        Robot.stilt.runWheels(OI.joy1.getRawAxis(RobotMap.Controller.LY));
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
}
