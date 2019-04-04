package frc.robot.commands.elevator;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHatch2 extends Command {

    public ElevatorHatch2() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
        Robot.elevator.hatchRocket2();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}