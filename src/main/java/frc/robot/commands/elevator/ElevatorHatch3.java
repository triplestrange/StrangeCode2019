package frc.robot.commands.elevator;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHatch3 extends Command {

    public ElevatorHatch3() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
        Robot.elevator.hatchRocket3();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}