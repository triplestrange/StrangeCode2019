package frc.robot.commands.elevator;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCargo1 extends Command {

    public ElevatorCargo1() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
        Robot.elevator.cargoRocket1();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}