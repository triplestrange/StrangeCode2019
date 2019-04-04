package frc.robot.commands.cargo;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class CargoIntakeIn extends Command {
    
    public double y;
    public CargoIntakeIn() {
    }

    @Override
    public void execute() {
        y = OI.joy2.getRawAxis(RobotMap.Controller.LT);
        if (Robot.elevator.clearForCargo() && !Robot.hatch.hatchExtended)
            Robot.cargo.rollIntake(y);
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