/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Climb extends PIDSubsystem{

    TalonSRX climb = new TalonSRX(RobotMap.Climb.ARM_MOTOR);
    VictorSPX wheels = new VictorSPX(RobotMap.Climb.ROLL_WHEELS);
    public DoubleSolenoid climbThings = new DoubleSolenoid(RobotMap.Climb.CLIMB_EXTEND, RobotMap.Climb.CLIMB_RETRACT);
    
    public Climb() {
        super("Climb", 0.1, 0, 0);
        climb.configFactoryDefault();
        wheels.configFactoryDefault();
        climb.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void initDefaultCommand() {
    }

    public void move() {
        if (OI.joy4.getRawButton(RobotMap.Controller.A)) {
            climbThings.set(Value.kForward);
        }
        if (OI.joy4.getRawButton(RobotMap.Controller.B)) {
            climbThings.set(Value.kReverse);
        }
        climb.set(ControlMode.PercentOutput, OI.joy4.getRawAxis(RobotMap.Controller.LY));
        wheels.set(ControlMode.PercentOutput, OI.joy4.getRawAxis(RobotMap.Controller.RY));
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.navxGyro.getPitch();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
