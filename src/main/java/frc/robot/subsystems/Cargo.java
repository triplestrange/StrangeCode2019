package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cargo extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static WPI_VictorSPX mCargoHandlerR = new WPI_VictorSPX(RobotMap.Controller.RIGHT_TRIGGER);
	public static WPI_VictorSPX mCargoHandlerL = new WPI_VictorSPX(RobotMap.Controller.LEFT_TRIGGER);
	public static WPI_VictorSPX mCargoIntakeL = new WPI_VictorSPX(RobotMap.Controller.LEFT_BUMPER);
	

	
	Joystick _joy2;
	private double _speed = RobotMap.CargoHandler.defaultSpeed;
	
	
	public Cargo(Joystick joy2){
		_joy2 = joy2;
	}

	public Cargo(Joystick joy2, double speed){
		_joy2 = joy2;
		_speed = speed;
	}

	public void move() {
		if (_joy2.getRawButton(RobotMap.Controller.LEFT_TRIGGER) && (_joy2.getRawButton(RobotMap.Controller.LEFT_BUMPER)) 
            && (!_joy2.getRawButton(RobotMap.Controller.RIGHT_TRIGGER))) {
            double speed = _joy2.getMagnitude();
            Robot.cargo.rollIn(speed, true);
        }
        else if (_joy2.getRawButton(RobotMap.Controller.LEFT_TRIGGER) && (!_joy2.getRawButton(RobotMap.Controller.RIGHT_TRIGGER))) {
            double speed = _joy2.getMagnitude();
            Robot.cargo.rollIn(speed, false);
        }
        else if(_joy2.getRawButton(RobotMap.Controller.LEFT_BUMPER) && (!_joy2.getRawButton(RobotMap.Controller.RIGHT_TRIGGER))) {
            double speed = _joy2.getMagnitude();
            Robot.cargo.bumperRollIn(speed, true); 
        }
        else if(_joy2.getRawButton(RobotMap.Controller.RIGHT_BUMPER) && (!_joy2.getRawButton(RobotMap.Controller.RIGHT_TRIGGER))) {
            double speed = _joy2.getMagnitude();
            Robot.cargo.bumperRollOut(speed, true); 
        }
        else if (_joy2.getRawButton(RobotMap.Controller.RIGHT_TRIGGER) && (_joy2.getRawButton(RobotMap.Controller.RIGHT_BUMPER)) 
            && (!_joy2.getRawButton(RobotMap.Controller.LEFT_TRIGGER))) {
            double speed = _joy2.getMagnitude();
            Robot.cargo.rollIn(speed, true);
        }
        else if(_joy2.getRawButton(RobotMap.Controller.RIGHT_TRIGGER) && (!_joy2.getRawButton(RobotMap.Controller.LEFT_TRIGGER))) {
            double speed = _joy2.getMagnitude();
            Robot.cargo.rollOut(speed, false);
        }
        else {
            end();
        }   
		
	}

	// bumper = top
	// trigger = bottom	
	// LEFT_TRIGGER = in
	// RIGHT_TRIGGER = out
	public void rollIn(double speed, boolean hasCargoIntake) {

		this._speed = speed;
		mCargoHandlerL.set(speed);
		mCargoHandlerR.set(speed);
		mCargoIntakeL.set(speed);

		double leftTopButton = _joy2.getRawAxis(RobotMap.Controller.LEFT_TRIGGER);
		mCargoHandlerL.set(leftTopButton);
		mCargoHandlerR.set(leftTopButton);
		
		double leftBottomButton = _joy2.getRawAxis(RobotMap.Controller.LEFT_BUMPER);
		mCargoIntakeL.set(leftBottomButton);
	}

	public void rollOut(double speed, boolean hasCargoIntake) {
		
		this._speed = speed;
		mCargoHandlerL.set(speed);
		mCargoHandlerR.set(speed);
		
		double rightTopButton = _joy2.getRawAxis(4);
		mCargoHandlerL.set(rightTopButton);
		mCargoHandlerR.set(rightTopButton);
	}
	public void bumperRollIn(double speed, boolean hasCargoIntake) {
		
		this._speed = speed;
		mCargoIntakeL.set(speed);
		
		double leftBottomButton = _joy2.getRawAxis(3);
		mCargoIntakeL.set(leftBottomButton);
	}
	public void bumperRollOut(double speed, boolean hasCargoIntake) {
		
		this._speed = speed;
		mCargoIntakeL.set(-speed);
		
		double rightBottomButton = _joy2.getRawAxis(4);
		mCargoIntakeL.set(rightBottomButton);
		}
	public void Intake() {
		double leftTopButton = _joy2.getRawAxis(3);
		mCargoHandlerL.set(leftTopButton);
		double rightTopButton = _joy2.getRawAxis(4);
		mCargoHandlerR.set(rightTopButton);
	}
	public void end() {
		Cargo.mCargoHandlerR.set(0);
        Cargo.mCargoHandlerL.set(0);
        Cargo.mCargoIntakeL.set(0);
	}


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

