package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.hatch.*;
import frc.robot.commands.swerve.*;

public class OI {
    public static Joystick joy1 = new Joystick(0);
    Button lockSwerve = new JoystickButton(joy1, RobotMap.Controller.A);
    Button fieldOrient = new JoystickButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new JoystickButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    // Button runAuto = new JoystickButton(joy1, RobotMap.Controller.B);
    Button visionButton = new JoystickButton(joy1, RobotMap.Controller.X);
    Button hatchleft = new POVButton(joy1, RobotMap.Controller.LEFT);
    Button hatchup = new POVButton(joy1, RobotMap.Controller.UP);
    Button hatchright = new POVButton(joy1, RobotMap.Controller.RIGHT);

    public static Joystick joy2 = new Joystick(1);
    Button hatchIn = new JoystickButton(joy2, RobotMap.Controller.LEFT_BUMPER);
    Button hatchOut = new JoystickButton(joy2, RobotMap.Controller.RIGHT_BUMPER);


    public static Joystick joy3 = new Joystick(2);

    public OI() {
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        // runAuto.whenPressed(new PathTesting());
        visionButton.whileHeld(new SwerveDriveWithVisionRight());

        hatchleft.whenPressed(new HatchLeft());
        hatchright.whenPressed(new HatchRight());
        hatchup.whenPressed(new HatchUp());
        hatchIn.whenPressed(new HatchPistonIn());
        hatchOut.whenPressed(new HatchPistonOut());
    }
}