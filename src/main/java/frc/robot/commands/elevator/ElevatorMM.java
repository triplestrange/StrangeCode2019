package frc.robot.commands.elevator;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMM extends Command {
    private double position;
    public ElevatorMM(double setpoint) {
        requires(Robot.elevator);
        position = setpoint;
    }

    @Override
    public void execute() {
        Robot.elevator.startMM(position);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}