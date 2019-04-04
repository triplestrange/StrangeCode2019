package frc.robot.commands.elevator;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCargoGround extends Command {

    public ElevatorCargoGround() {
        requires(Robot.elevator);
    }

    @Override
    public void execute() {
            Robot.elevator.cargoIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}