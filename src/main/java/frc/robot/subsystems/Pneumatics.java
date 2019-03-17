package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.pneumatics.PneumaticsDefault;

public class Pneumatics extends Subsystem {

    DoubleSolenoid rearPiston = new DoubleSolenoid(4, 3);
    DoubleSolenoid frontPiston = new DoubleSolenoid(6, 7);
    WPI_VictorSPX climbWheels = new WPI_VictorSPX(24);
    Solenoid shortCargo = new Solenoid(5);
    WPI_VictorSPX mGovernor = new WPI_VictorSPX(RobotMap.Governor.mGovernor);

    
    public Pneumatics() {
        super();
        
        climbWheels.setNeutralMode(NeutralMode.Brake);
        mGovernor.setNeutralMode(NeutralMode.Brake);
        allOut();
    }

    public void move() {
        double controlGovernor = OI.joy1.getRawAxis(RobotMap.Controller.RT);
        double controlWheels = OI.joy1.getRawAxis(RobotMap.Controller.LT);
        
        if (OI.joy1.getRawButton(RobotMap.Controller.Y)) {
            frontPiston.set(DoubleSolenoid.Value.kForward);
            rearPiston.set(DoubleSolenoid.Value.kForward);
        }
        else if (OI.joy1.getRawButton(RobotMap.Controller.B)) {
            frontPiston.set(DoubleSolenoid.Value.kReverse);
            rearPiston.set(DoubleSolenoid.Value.kForward);            
        }
        else if (OI.joy1.getRawButton(RobotMap.Controller.X)) {
            frontPiston.set(DoubleSolenoid.Value.kReverse);
            rearPiston.set(DoubleSolenoid.Value.kReverse);
        }
        if (OI.joy1.getRawButton(RobotMap.Controller.RIGHT_FACE)) {
            if (Math.abs(controlGovernor) > 0.1) {
                mGovernor.set(controlGovernor); // If it doesn't work, change it to controlGovernor
            } else {
                mGovernor.set(0);
            }
            if (Math.abs(controlWheels) > 0.1) {
                climbWheels.set(controlWheels * 0.75); // If it doesn't work, change it to controlGovernor
            } else {
                climbWheels.set(0);
            }
        }
        if (OI.joy1.getRawButton(RobotMap.Controller.LEFT_FACE)) {
            if (Math.abs(controlGovernor) > 0.1) {
                mGovernor.set(controlGovernor * -0.75); // If it doesn't work, change it to controlGovernor
            } else {
                mGovernor.set(0);
            }
            if (Math.abs(controlWheels) > 0.1) {
                climbWheels.set(controlWheels * -0.75); // If it doesn't work, change it to controlGovernor
            } else {
                climbWheels.set(0);
            }
        }
        
    }

    public void allOut() {
        rearPiston.set(DoubleSolenoid.Value.kReverse);
        frontPiston.set(DoubleSolenoid.Value.kReverse);
        shortCargo.set(true);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PneumaticsDefault());
    }
}
