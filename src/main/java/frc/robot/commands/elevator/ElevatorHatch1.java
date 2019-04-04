package frc.robot.commands.elevator;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHatch1 extends Command {

    public ElevatorHatch1() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
            Robot.elevator.hatchRocket1();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}