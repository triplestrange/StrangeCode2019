package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.cargo.CargoWithJoy;
import frc.robot.OI;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cargo extends Subsystem {

    public static WPI_VictorSPX mCargoHandlerL = new WPI_VictorSPX(RobotMap.Cargo.L_MOTOR);
    public static WPI_VictorSPX mCargoHandlerR = new WPI_VictorSPX(RobotMap.Cargo.R_MOTOR);
    public static WPI_VictorSPX mCargoIntake = new WPI_VictorSPX(RobotMap.Cargo.MOTOR);

    public Cargo() {
        super();
        mCargoHandlerL.setNeutralMode(NeutralMode.Brake);
        mCargoHandlerR.setNeutralMode(NeutralMode.Brake);
        mCargoIntake.setNeutralMode(NeutralMode.Coast);
    }

    public void move() {
        double speedin = OI.joy2.getRawAxis(2);
        double speedout = OI.joy2.getRawAxis(3);

        if (speedin > .05) {
            rollWheels(speedin);
        } else if (speedout > .05) {
            rollWheels(-speedout);
        } else {
            stop();
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

    public void initDefaultCommand() {
        setDefaultCommand(new CargoWithJoy());
    }
}
