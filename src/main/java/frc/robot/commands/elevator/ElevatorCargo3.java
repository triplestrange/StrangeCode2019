package frc.robot.commands.elevator;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCargo3 extends Command {

    public ElevatorCargo3() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
        Robot.elevator.cargoRocket3();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}