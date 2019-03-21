package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.util.buttons.*;
import frc.robot.commands.auto.PathTesting;
import frc.robot.commands.hatch.*;
import frc.robot.commands.swerve.*;

public class OI {
    public static Joystick joy1 = new Joystick(0);
    Button lockSwerve = new EnabledButton(joy1, RobotMap.Controller.A);
    Button fieldOrient = new EnabledButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new EnabledButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    Button runAuto = new EnabledButton(joy1, RobotMap.Controller.B);
    Button visionButton = new EnabledButton(joy1, RobotMap.Controller.X);

    public static Joystick joy2 = new Joystick(1);
    Button hatchIn = new EnabledButton(joy2, RobotMap.Controller.LEFT_BUMPER);
    Button hatchOut = new EnabledButton(joy2, RobotMap.Controller.RIGHT_BUMPER);

    public OI() {
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        runAuto.whenPressed(new PathTesting());
        visionButton.whileHeld(new SwerveDriveWithVisionRight());

        hatchIn.whenPressed(new HatchPistonIn());
        hatchOut.whenPressed(new HatchPistonOut());
    }
}