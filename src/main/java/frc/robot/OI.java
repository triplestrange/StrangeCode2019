package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.auto.PathTesting;
import frc.robot.commands.hatch.*;
import frc.robot.commands.swerve.*;

public class OI {
    public static Joystick joy1 = new Joystick(0);
    // Button lowSpeed = new JoystickButton(joy1, RobotMap.Controller.LEFT_TRIGGER);
    // Button highSpeed = new JoystickButton(joy1, RobotMap.Controller.RIGHT_TRIGGER);
    Button lockSwerve = new JoystickButton(joy1, RobotMap.Controller.A);
    Button fieldOrient = new JoystickButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new JoystickButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    Button runAuto = new JoystickButton(joy1, RobotMap.Controller.B);
    Button visionButton = new JoystickButton(joy1, RobotMap.Controller.X);

    public static Joystick joy2 = new Joystick(1);
    // Button hatchleft = new JoystickButton(joy2, RobotMap.Controller.X);
    // Button hatchup = new JoystickButton(joy2, RobotMap.Controller.Y);
    // Button hatchright = new JoystickButton(joy2, RobotMap.Controller.B);

    public OI() {
        // lowSpeed.whileHeld(new SwerveSetLowSpeed());
        // highSpeed.whileHeld(new SwerveSetHighSpeed());
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        runAuto.whenPressed(new PathTesting());
        visionButton.whileHeld(new SwerveDriveWithVision());

        // hatchleft.whileHeld(new HatchLeft());
        // hatchright.whileHeld(new HatchRight());
        // hatchup.whileHeld(new HatchUp());
    }
}