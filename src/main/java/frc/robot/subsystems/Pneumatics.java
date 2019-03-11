package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;

public class Pneumatics extends Subsystem {

    DoubleSolenoid rearPiston = new DoubleSolenoid(4, 3);
    DoubleSolenoid frontPiston = new DoubleSolenoid(6, 7);
    WPI_VictorSPX climbWheels = new WPI_VictorSPX(24);
    Solenoid shortCargo = new Solenoid(5);

    WPI_VictorSPX mGovernor = new WPI_VictorSPX(RobotMap.Governor.mGovernor);

    public Pneumatics() {
        super();
    }

    // public void allIn() {
    //     climbWheels.setNeutralMode(NeutralMode.Brake);
    //     frontPiston.set(DoubleSolenoid.Value.kForward);
    //     // Timer.delay(2.0);
    //     rearPiston.set(DoubleSolenoid.Value.kForward);
    // }

    public void move() {
        double controlGovernor = OI.joy3.getRawAxis(4);

        climbWheels.setNeutralMode(NeutralMode.Brake);
        if (OI.joy3.getRawButton(RobotMap.Controller.Y)) {
            rearPiston.set(DoubleSolenoid.Value.kForward);
            // Timer.delay(0.35); // They didn't want a delay, even though it was smol
            frontPiston.set(DoubleSolenoid.Value.kForward);
            
        }
        if (OI.joy3.getRawButton(RobotMap.Controller.B)) {
            frontPiston.set(DoubleSolenoid.Value.kReverse);
            rearPiston.set(DoubleSolenoid.Value.kForward);
            
        }
        if (OI.joy3.getRawButton(RobotMap.Controller.A)) {
            frontPiston.set(DoubleSolenoid.Value.kReverse);
            rearPiston.set(DoubleSolenoid.Value.kReverse);
            
        }
        if (Math.abs(OI.joy3.getRawAxis(0)) > 0.1) {
            climbWheels.set(OI.joy3.getRawAxis(0));
        } else {
            climbWheels.set(0);
        }

        mGovernor.setNeutralMode(NeutralMode.Brake);
        if (Math.abs(controlGovernor) > 0.1) {
            mGovernor.set(controlGovernor * 0.75); // If it doesn't work, change it to controlGovernor
        } else {
            mGovernor.set(0);
        }


    }

    public void allOut() {
        // rearPiston.set(DoubleSolenoid.Value.kReverse);
        // frontPiston.set(DoubleSolenoid.Value.kReverse);
        shortCargo.set(true);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
