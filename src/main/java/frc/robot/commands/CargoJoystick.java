package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Cargo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CargoJoystick extends Command {
  
    double duration;
    double startTime;
    double speed;
    Cargo _cargo;


    // public CargoHandler(double duration, double speed) {
    //     requires(Robot.cargo);
    //     this.duration = duration;
    //     this.speed = speed;
    
    //     }
    public CargoJoystick() {
        requires(Robot.cargo);        
        _cargo = new Cargo(Robot.joy2);
    }

    @Override
    public void execute() {
       _cargo.move();
    }
        


    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end() {
		_cargo.end();
	}
}
