package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.util.buttons.*;
import frc.robot.commands.auto.PathTesting;
import frc.robot.commands.hatch.*;
import frc.robot.commands.swerve.*;

public class OI {
    public static Joystick joy1 = new Joystick(0);
    Button lockSwerve = new EnabledButton(joy1, RobotMap.Controller.X);
    Button fieldOrient = new EnabledButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new EnabledButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    Button hatchVision = new EnabledButton(joy1, RobotMap.Controller.B);
    Button cargoTapeVision = new EnabledButton(joy1, RobotMap.Controller.X);
    Button cargoVision = new EnabledButton(joy1, RobotMap.Controller.Y);
    Button runAuto = new EnabledButton(joy1, RobotMap.Controller.RIGHT_FACE);
    Button hatchExtend = new JoystickAxisButton(joy1, RobotMap.Controller.RT);

    public static Joystick joy2 = new Joystick(1);
    Button hatchIn = new EnabledButton(joy2, RobotMap.Controller.LEFT_BUMPER);
    Button hatchOut = new EnabledButton(joy2, RobotMap.Controller.RIGHT_BUMPER);

    public static Joystick joy4 = new Joystick(3);
    public OI() {
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        runAuto.whenPressed(new PathTesting());
        hatchVision.whileHeld(new SwerveDriveWithVisionRight());
        hatchExtend.toggleWhenPressed(new HatchPistonExtend());

        hatchIn.whenPressed(new HatchPistonIn());
        hatchOut.whenPressed(new HatchPistonOut());
    }
}