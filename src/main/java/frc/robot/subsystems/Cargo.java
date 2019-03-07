package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.cargo.CargoWithJoy;
import frc.robot.OI;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
//  That's what I just added ^^^^^^^^
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cargo extends Subsystem {

    public static WPI_VictorSPX mCargoHandlerL = new WPI_VictorSPX(RobotMap.Cargo.L_MOTOR);
    public static WPI_VictorSPX mCargoHandlerR = new WPI_VictorSPX(RobotMap.Cargo.R_MOTOR);
    public static WPI_VictorSPX mCargoIntake = new WPI_VictorSPX(RobotMap.Cargo.MOTOR);
    DoubleSolenoid longCargo = new DoubleSolenoid(1, 0);

    public Cargo() {
        super();
        mCargoHandlerL.setNeutralMode(NeutralMode.Brake);
        mCargoHandlerR.setNeutralMode(NeutralMode.Brake);
        mCargoIntake.setNeutralMode(NeutralMode.Coast);
    }

    public void move() {
        double speedin = OI.joy2.getRawAxis(2);
        double speedout = OI.joy2.getRawAxis(3);

        if (speedin > .25) {
            rollWheels(-speedin);
            longCargo.set(DoubleSolenoid.Value.kForward);
        } else if (speedout > .25) {
            rollWheels(speedout);
            longCargo.set(DoubleSolenoid.Value.kReverse);
        } else {
            stop();
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
