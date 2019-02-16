package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.auto.PathTesting;
import frc.robot.commands.auto.loriPathTesting;
import frc.robot.commands.swerve.*;

public class OI {
    Button lowSpeed = new JoystickButton(Robot.joy1, RobotMap.Controller.LEFT_TRIGGER);
    Button highSpeed = new JoystickButton(Robot.joy1, RobotMap.Controller.RIGHT_TRIGGER);
    Button lockSwerve = new JoystickButton(Robot.joy1, RobotMap.Controller.A);
    Button fieldOrient = new JoystickButton(Robot.joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new JoystickButton(Robot.joy1, RobotMap.Controller.LEFT_BUMPER);
    Button runAuto = new JoystickButton(Robot.joy1, RobotMap.Controller.B);
    Button visionButton = new JoystickButton(Robot.joy1, RobotMap.Controller.X);

    public OI() {
        lowSpeed.whileHeld(new SwerveSetLowSpeed());
        highSpeed.whileHeld(new SwerveSetHighSpeed());
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        runAuto.whenPressed(new PathTesting());
        visionButton.whileHeld(new SwerveDriveWithVision());
    }
}