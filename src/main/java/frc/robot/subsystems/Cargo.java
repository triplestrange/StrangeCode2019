package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.cargo.CargoWithJoy;

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

    /*
    * @param mCargoHandlerL Left Cargo Handler Motor
    * @param mCargoHandlerR Right Gargo Handler Motor
    */
    public Cargo() {
        super();
        mCargoHandlerL.setNeutralMode(NeutralMode.Brake);
        mCargoHandlerR.setNeutralMode(NeutralMode.Brake);
        mCargoIntake.setNeutralMode(NeutralMode.Coast);
        shortCargo.set(true);
    }

    public void joyControl(double y) {
        if (y > 0.35) {
            longCargo.set(DoubleSolenoid.Value.kForward);
        } else if (y < 0.35) {
            longCargo.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public void extendLong() {
        longCargo.set(DoubleSolenoid.Value.kForward);
    }

    public void retractLong() {
        longCargo.set(DoubleSolenoid.Value.kReverse);
    }

    public void extendShort() {
        shortCargo.set(true);
    }

    public void retractShort() {
        shortCargo.set(false);
    }

    public void handler(double speed) {
        mCargoHandlerL.set(speed);
        mCargoHandlerR.set(-speed);
    }

    public void rollWheels(double speed) {
        mCargoHandlerL.set(-speed);
        mCargoHandlerR.set(speed);
        mCargoIntake.set(speed);
    }

    public void rollIntake(double speed) {
        mCargoIntake.set(-speed);
    }
    
    public void rollHandler(double speed) {
        mCargoHandlerL.set(speed);
        mCargoHandlerR.set(-speed);
    }

    public void stop() {
        rollWheels(0);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new CargoWithJoy());
    }
}