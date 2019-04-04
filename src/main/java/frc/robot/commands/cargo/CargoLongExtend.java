package frc.robot.commands.cargo;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class CargoLongExtend extends Command {

    public CargoLongExtend() {
        requires(Robot.cargo);
    }

    @Override
    public void execute() {
        if (Robot.elevator.clearForCargo() && !Robot.hatch.hatchExtended)
            Robot.cargo.extendLong();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}