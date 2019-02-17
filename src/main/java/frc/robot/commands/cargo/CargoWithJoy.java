package frc.robot.commands.cargo;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class CargoWithJoy extends Command {
    
    public CargoWithJoy() {
        requires(Robot.cargo);
    }

    @Override
    public void execute() {
       Robot.cargo.move();
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        Robot.cargo.stop();
    }
}
