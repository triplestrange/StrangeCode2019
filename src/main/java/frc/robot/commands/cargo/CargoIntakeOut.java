package frc.robot.commands.cargo;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class CargoIntakeOut extends Command {
    
    public double y;
    public CargoIntakeOut() {
    }

    @Override
    public void execute() {
        y = OI.joy2.getRawAxis(RobotMap.Controller.RT);
        Robot.cargo.rollIntake(-y);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(y) < 0.1;
    }
    
    public void end() {
        Robot.cargo.rollIntake(0);
    }

    @Override
    public void interrupted() {
        end();
    }
}