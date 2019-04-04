package frc.robot.commands.elevator;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorReset extends Command {

    public ElevatorReset() {
    }

    @Override
    public void execute() {
        Robot.elevator.resetEncoder();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}