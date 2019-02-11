package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.profiling.TrapezoidProfile;
import frc.robot.profiling.ProfileFollower;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HatchIntake extends Subsystem {

    WPI_TalonSRX hatchMotor = new WPI_TalonSRX(RobotMap.HatchIntake.hatchMotor);
    Joystick joy2;

    public HatchIntake() {
        hatchMotor.configNominalOutputForward(0, 0);
		hatchMotor.configNominalOutputReverse(0, 0);
		hatchMotor.configPeakOutputForward(1, 0);
		hatchMotor.configPeakOutputReverse(-1, 0);

		//Set Motion Magic gains in slot0 - see documentation
		hatchMotor.selectProfileSlot(0, 0);
		hatchMotor.config_kF(0, 0, 0);
		hatchMotor.config_kP(0, 0, 0);
		hatchMotor.config_kI(0, 0, 0);
        hatchMotor.config_kD(0, 0, 0);    
    }

    public void move(Joystick joy2) {
        this.joy2 = joy2;
        double button = joy2.getY(); //?
         
    }

    @Override
    protected void initDefaultCommand() {

    }
    
}
