package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.cargo.CargoWithJoy;
import frc.robot.OI;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cargo extends Subsystem {

    private WPI_VictorSPX mCargoHandlerL = new WPI_VictorSPX(RobotMap.Cargo.L_MOTOR);
    private WPI_VictorSPX mCargoHandlerR = new WPI_VictorSPX(RobotMap.Cargo.R_MOTOR);
    private WPI_VictorSPX mCargoIntake = new WPI_VictorSPX(RobotMap.Cargo.MOTOR);
    private DoubleSolenoid longCargo = new DoubleSolenoid(1, 0);
    private Solenoid shortCargo = new Solenoid(5);

    public Cargo() {
        super();
        mCargoHandlerL.setNeutralMode(NeutralMode.Brake);
        mCargoHandlerR.setNeutralMode(NeutralMode.Brake);
        mCargoIntake.setNeutralMode(NeutralMode.Coast);
        shortCargo.set(true);
    }

    public void move() {
        double speedin = OI.joy2.getRawAxis(2);
        double speedout = OI.joy2.getRawAxis(3);
        boolean cargo = Robot.elevator.clearForCargo();

        if (speedin > .25 && cargo == true && !Robot.hatch.hatchExtended) {
            rollWheels(-speedin);
            longCargo.set(DoubleSolenoid.Value.kForward);
        } 
        else if (speedout > .25 && cargo == true) {
            rollWheels(speedout);
        }
        else if (speedout > .25 && cargo == false) {
            mCargoHandlerL.set(-speedout);
            mCargoHandlerR.set(speedout);
        }
        else {
            stop();
        }
        if (cargo == false) {
            longCargo.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void rollWheels(double speed) {
        mCargoHandlerL.set(-speed);
        mCargoHandlerR.set(speed);
        mCargoIntake.set(speed);

    }
    public void stop() {
        rollWheels(0);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new CargoWithJoy());
    }
}
