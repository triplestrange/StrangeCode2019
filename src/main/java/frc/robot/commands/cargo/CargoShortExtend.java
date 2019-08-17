package frc.robot.commands.cargo;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class CargoShortExtend extends Command {
    
    public CargoShortExtend() {
    }

    @Override
    public void execute() {
        Robot.cargo.extendShort();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    @Override
    public void end() {
        Robot.cargo.retractShort();
    }

    @Override
    public void interrupted() {
        end();
    }
}