package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorWithJoy extends Command {
    public ElevatorWithJoy() {
        requires(Robot.elevator);
    }

    @Override
    protected void execute() {
        Robot.elevator.joyControl();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.elevator.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}