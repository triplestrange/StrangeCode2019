package frc.robot.commands.cargo;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class CargoHandlerIn extends Command {
    
    public double y;
    public CargoHandlerIn() {
    }

    @Override
    public void execute() {
       y = OI.joy2.getRawAxis(RobotMap.Controller.LT);
       Robot.cargo.rollHandler(y);
    }
    @Override
    public boolean isFinished() {
        return Math.abs(y)<0.1;
    }

    @Override
    public void end() {
        Robot.cargo.rollHandler(0);
    }

    @Override
    public void interrupted() {
        end();
    }
}