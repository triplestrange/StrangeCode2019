package frc.robot.commands.elevator;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCargoShip extends Command {

    public ElevatorCargoShip() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
        Robot.elevator.cargoShip();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}