package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorWithJoy;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
    private WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.Elevator.elevator1);
    private WPI_VictorSPX elevator2 = new WPI_VictorSPX(RobotMap.Elevator.elevator2);
    private double position;
    private boolean elevatorMM = false;

    public Elevator() {
        elevator1.configFactoryDefault();
        elevator2.configFactoryDefault();
        elevator1.configPeakCurrentLimit(15, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configPeakCurrentDuration(500, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configContinuousCurrentLimit(10, RobotMap.DEFAULT_TIMEOUT);
        elevator1.enableCurrentLimit(true);

        elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.setSelectedSensorPosition(RobotMap.Elevator.START_POSITION, 0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.setSensorPhase(false);
        elevator1.setInverted(false);

        elevator1.setNeutralMode(NeutralMode.Brake);
        elevator2.setNeutralMode(NeutralMode.Brake);

        elevator1.configNominalOutputForward(0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configNominalOutputReverse(0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configPeakOutputForward(1, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configPeakOutputReverse(-1, RobotMap.DEFAULT_TIMEOUT);

        elevator1.configForwardSoftLimitThreshold(RobotMap.Elevator.CARGO_3ROCKET, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configForwardSoftLimitEnable(true);

        elevator1.selectProfileSlot(0, 0);
        elevator1.config_kF(0, 1023.0/4667, RobotMap.DEFAULT_TIMEOUT);
        elevator1.config_kP(0, 0.1, RobotMap.DEFAULT_TIMEOUT);
        elevator1.config_kI(0, 0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.config_kD(0, 0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configMotionCruiseVelocity(4667, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configMotionAcceleration(4667, RobotMap.DEFAULT_TIMEOUT);

        elevator2.follow(elevator1);
        elevator2.setInverted(InvertType.FollowMaster);
    }

    public void move() {
        double y = OI.joy2.getRawAxis(1);
        if (OI.joy2.getRawButton(RobotMap.Controller.B)) {
        elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.HATCH_2ROCKET);
        elevatorMM = true;
        }
        if (OI.joy2.getRawButton(RobotMap.Controller.A)) {
            if (Robot.hatch.hatchExtended) {
                elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.HATCH_1ROCKET);
            }
            else {
                elevator1.set(ControlMode.MotionMagic, 0);
            }
            elevatorMM = true;
        }
        if (OI.joy2.getRawButton(RobotMap.Controller.Y)) {
            elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.HATCH_3ROCKET);
            elevatorMM = true;
        }
        if (OI.joy2.getRawButton(RobotMap.Controller.X)) {
            elevator1.set(ControlMode.MotionMagic, 0);
            elevatorMM = true;
        }
        if (OI.joy2.getPOV() == 0) {
        elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_3ROCKET);
        elevatorMM = true;
        }
        if (OI.joy2.getPOV() == 90) {
        elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_2ROCKET);
        elevatorMM = true;
        }
        if (OI.joy2.getPOV() == 180) {
        elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_1ROCKET);
        elevatorMM = true;
        }
        if (OI.joy2.getPOV() == 270) {
        elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_SHIP);
        elevatorMM = true;
        }
        if (OI.joy2.getRawButton(RobotMap.Controller.RIGHT_FACE)) {
            resetEncoder();
        }

        if (Math.abs(y) > 0.15) {
            elevatorMM = false;
            elevator1.set(-1 * y);
            position = getDistance();
        } else if (elevatorMM == false) {
            elevator1.set(0);
        }
    }

    public void resetEncoder() {
        elevator1.setSelectedSensorPosition(0, 0, RobotMap.DEFAULT_TIMEOUT);
    }

    public boolean clearForCargo() {
        return getDistance() < RobotMap.Elevator.CARGO_1ROCKET + 2000;
    }

    public boolean clearForHatch() {
        return getDistance() > (RobotMap.Elevator.CARGO_1ROCKET - 2000)/2;
    }

    public void cargoElevator() {
        elevator1.set(ControlMode.MotionMagic, RobotMap.Elevator.CARGO_1ROCKET);
        elevatorMM = true;
    }

    public boolean getMM() {
        return elevatorMM;
    }

    public void setMM(boolean newMM) {
        elevatorMM = newMM;
    }

    public void smartDash() {
        SmartDashboard.putNumber("Elevator Encoder", getDistance());
        SmartDashboard.putBoolean("Clear for Cargo", clearForCargo());
    }

    public double getDistance() {
        return elevator1.getSelectedSensorPosition(0);
    }

    public void startMM(double setpoint, boolean joy) {
        elevator1.set(ControlMode.MotionMagic, setpoint);
        elevatorMM = joy;
    }

    public void stop() {
        elevator1.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorWithJoy());
    }
}