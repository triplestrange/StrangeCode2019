package frc.robot.commands.elevator;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCargo2 extends Command {

    public ElevatorCargo2() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
        Robot.elevator.cargoRocket2();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}