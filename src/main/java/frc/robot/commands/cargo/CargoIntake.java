package frc.robot.commands.cargo;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class CargoIntake extends Command {

    public double y;
    private int dir;

    public CargoIntake(int direction) {
        dir = direction;
    }

    @Override
    public void execute() {
        if (dir == 1)
            y = OI.joy2.getRawAxis(RobotMap.Controller.LT);
        else if (dir == -1)
            y = OI.joy2.getRawAxis(RobotMap.Controller.RT)*(-1);
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