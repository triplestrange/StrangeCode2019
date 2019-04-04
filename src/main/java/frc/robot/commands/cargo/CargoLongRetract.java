package frc.robot.commands.cargo;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class CargoLongRetract extends Command {

    public CargoLongRetract() {
        requires(Robot.cargo);
    }

    @Override
    public void execute() {
        Robot.cargo.retractLong();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}