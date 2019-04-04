package frc.robot.commands.cargo;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class CargoShortExtend extends Command {
    
    public CargoShortExtend() {
        requires(Robot.cargo);
    }

    @Override
    public void execute() {
        Robot.cargo.extendShort();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}