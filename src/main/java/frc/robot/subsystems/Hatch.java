package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.hatch.HatchWithJoy;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hatch extends Subsystem {

    Solenoid piston = new Solenoid(2);

    public Hatch() {
    }

    public void move() {
    }

    public void smartdash() {
    }

    public void grab() {
        piston.set(true);
    }
    
    public void place() {
        piston.set(false);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
