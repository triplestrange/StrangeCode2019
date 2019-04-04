package frc.robot.subsystems;

import frc.robot.OI;
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
    public WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.Elevator.elevator1);
    private WPI_VictorSPX elevator2 = new WPI_VictorSPX(RobotMap.Elevator.elevator2);
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
        elevator1.config_kF(0, 1023.0 / 4667, RobotMap.DEFAULT_TIMEOUT);
        elevator1.config_kP(0, 0.1, RobotMap.DEFAULT_TIMEOUT);
        elevator1.config_kI(0, 0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.config_kD(0, 0, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configMotionCruiseVelocity(4667, RobotMap.DEFAULT_TIMEOUT);
        elevator1.configMotionAcceleration(4667, RobotMap.DEFAULT_TIMEOUT);

        elevator2.follow(elevator1);
        elevator2.setInverted(InvertType.FollowMaster);
    }


    public void smartDash() {
        SmartDashboard.putNumber("Elevator Encoder", getDistance());
        SmartDashboard.putBoolean("Clear for Cargo", clearForCargo());
        SmartDashboard.putBoolean("Clear for Hatch", clearForHatch());
        SmartDashboard.putBoolean("Is MM running", getmm());
    }

    public void cargoRocket1() {
        startMM(RobotMap.Elevator.CARGO_1ROCKET);
    }

    public void cargoRocket2() {
        startMM(RobotMap.Elevator.CARGO_2ROCKET);
    }

    public void cargoRocket3() {
        startMM(RobotMap.Elevator.CARGO_3ROCKET);
    }

    public void cargoShip() {
        startMM(RobotMap.Elevator.CARGO_SHIP);
    }

    public void hatchRocket1() {
        startMM(RobotMap.Elevator.HATCH_1ROCKET);
    }

    public void hatchRocket2() {
        startMM(RobotMap.Elevator.HATCH_2ROCKET);
    }

    public void hatchRocket3() {
        startMM(RobotMap.Elevator.HATCH_3ROCKET);
    }

    public void cargoIntake() {
        startMM(0);
    }

    public void joyControl() {
        double y = OI.joy2.getRawAxis(RobotMap.Controller.LY);
        if (Math.abs(y) > 0.15) {
            elevatorMM = false;
            elevator1.set(-0.5 * y);
        } else if (elevatorMM == false) {
            elevator1.set(0);
        }
    }

    public void resetEncoder() {
        elevator1.setSelectedSensorPosition(0, 0, RobotMap.DEFAULT_TIMEOUT);
    }

    public void stop() {
        elevator1.set(0);
    }

    private void startMM(double setpoint) {
        elevator1.set(ControlMode.MotionMagic, setpoint);
        elevatorMM = true;
    }

    public double getDistance() {
        return elevator1.getSelectedSensorPosition(0);
    }

    public boolean clearForCargo() {
        return getDistance() < RobotMap.Elevator.CARGO_1ROCKET + 2000;
    }

    public boolean clearForHatch() {
        return getDistance() > (RobotMap.Elevator.CARGO_1ROCKET) / 2;
    }

    public boolean getmm() {
        return elevatorMM;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElevatorWithJoy());
    }
}