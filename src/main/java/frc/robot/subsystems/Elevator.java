package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorWithJoy;
import frc.robot.profiling.TrapezoidProfile;
import frc.robot.profiling.ProfileFollower;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.Elevator.elevator1);
    WPI_VictorSPX elevator2 = new WPI_VictorSPX(RobotMap.Elevator.elevator2);
    double distZero;

    public Elevator() {
        elevator1.configFactoryDefault();
        elevator2.configFactoryDefault();
        elevator1.configPeakCurrentLimit(15, 30);
		elevator1.configPeakCurrentDuration(0, 30);
		elevator1.configContinuousCurrentLimit(10, 30);
        elevator1.enableCurrentLimit(true); // Honor initial setting

        elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        elevator1.setSensorPhase(true);
        elevator1.setInverted(true);
        
        elevator1.setNeutralMode(NeutralMode.Brake);
        elevator2.setNeutralMode(NeutralMode.Brake);

        elevator1.configNominalOutputForward(0, 30);
        elevator1.configNominalOutputReverse(0, 30);
        elevator1.configPeakOutputForward(1, 30);
        elevator1.configPeakOutputReverse(-1, 30);

        // // Set Motion Magic gains in slot0 - see documentation
        elevator1.selectProfileSlot(0, 0);
        elevator1.config_kF(0, 0.30674662668665667166416791604198, 30);
        elevator1.config_kP(0, 0.03, 30);
        elevator1.config_kI(0, 0, 30);
        elevator1.config_kD(0, 0, 30);
        elevator1.configMotionCruiseVelocity(3335, 30);
        elevator1.configMotionAcceleration(3335, 30);
        
        elevator2.follow(elevator1);
        elevator2.setInverted(InvertType.OpposeMaster);
    }


    public void move() {
        double y = OI.joy2.getRawAxis(1);
        if ((y) > 0.1) {
            elevator1.set(-1*y);
        }
        else if (OI.joy2.getRawButton(RobotMap.Controller.B)) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.HATCH_2ROCKET);
        }
        else if (OI.joy2.getRawButton(RobotMap.Controller.A)) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.HATCH_1ROCKET);
        }
        else if (OI.joy2.getRawButton(RobotMap.Controller.Y)) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.HATCH_3ROCKET);
        }
        else if (OI.joy2.getPOV() == 0) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_3ROCKET);
        }
        else if (OI.joy2.getPOV() == 90) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_2ROCKET);
        }
        else if (OI.joy2.getPOV() == 180) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_1ROCKET);
        }
        else if (OI.joy2.getPOV() == 270) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_SHIP);
        }
        else if (OI.joy2.getRawButton(RobotMap.Controller.X)) {
            resetEncoder();
        }
        else {
            elevator1.set(ControlMode.MotionMagic, getDistance());
        }

        SmartDashboard.putNumber("Elevator Encoder", getDistance());
        
       
        
        
    }

    public void resetEncoder() {
		distZero += getDistance();
	}

	public double getDistance() {
		return elevator1.getSelectedSensorPosition() - distZero;
    }

    public void stop() {
        elevator1.set(0);
    }
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorWithJoy());
    }
}