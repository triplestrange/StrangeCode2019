package frc.robot.subsystems;

import frc.robot.OI;
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

        elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        
        elevator1.setNeutralMode(NeutralMode.Brake);
        elevator2.setNeutralMode(NeutralMode.Brake);

        // elevator1.configReverseSoftLimitThreshold(RobotMap.Elevator.CARGO_3ROCKET-2000, 30);
        // elevator1.configForwardSoftLimitThreshold(2000, 30);
        // elevator1.configForwardSoftLimitEnable(true, 30);
        // elevator1.configReverseSoftLimitEnable(true, 30);

        elevator2.follow(elevator1);
        elevator2.setInverted(InvertType.OpposeMaster);
        // // encoder.setDistancePerPulse(-(1.0 / 256) * (8.0 / 60) * (1.432 *
        // Math.PI));

        // elevator1.configNominalOutputForward(0, 0);
        // elevator1.configNominalOutputReverse(0, 0);
        // elevator1.configPeakOutputForward(1, 0);
        // elevator1.configPeakOutputReverse(-1, 0);

        // // Set Motion Magic gains in slot0 - see documentation
        // elevator1.selectProfileSlot(0, 0);
        // elevator1.config_kF(0, 0, 0);
        // elevator1.config_kP(0, 0, 0);
        // elevator1.config_kI(0, 0, 0);
        // elevator1.config_kD(0, 0, 0);
    }


    public void move() {
        double y = OI.joy2.getRawAxis(1);
        if (getDistance() < 2000 && y > 0.1) {
            elevator1.set(y * (0.5));
        }
        else if (getDistance() > 61424 && y < 0.1) {
            elevator1.set(y * (0.5));
        }
        else {
            
        }
        // if (Math.abs(y) > 0.1) {
        //     elevator1.set(y * (0.5));
        // }
        if (OI.joy2.getRawButton(RobotMap.Controller.A)) {
            resetEncoder();
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