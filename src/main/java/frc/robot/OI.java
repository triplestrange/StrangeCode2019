package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.util.buttons.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.cargo.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.swerve.*;
import frc.robot.commands.elevator.*;

public class OI {
    public static Joystick joy1 = new Joystick(0);
    Button lockSwerve = new EnabledButton(joy1, RobotMap.Controller.X);
    Button fieldOrient = new EnabledButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new EnabledButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    Button visionFoward = new JoystickAxisButton(joy1, RobotMap.Controller.LT);
    Button visionBack = new JoystickAxisButton(joy1, RobotMap.Controller.RT);
    Button runAuto = new EnabledButton(joy1, RobotMap.Controller.RIGHT_FACE);
    Button hatchExtend = new EnabledButton(joy1, RobotMap.Controller.LEFT_FACE);
    Button slow = new EnabledButton(joy1, RobotMap.Controller.JOY_RIGHT);

    public static Joystick joy2 = new Joystick(1);
    Button hatchIn = new EnabledButton(joy2, RobotMap.Controller.LEFT_BUMPER);
    Button hatchOut = new EnabledButton(joy2, RobotMap.Controller.RIGHT_BUMPER);
    Button hatch1 = new EnabledButton(joy2, RobotMap.Controller.A);
    Button hatch2 = new EnabledButton(joy2, RobotMap.Controller.B);
    Button hatch3 = new EnabledButton(joy2, RobotMap.Controller.Y);
    Button cargo1 = new JoystickPOVButton(joy2, 180);
    Button cargo2 = new JoystickPOVButton(joy2, 90);
    Button cargo3 = new JoystickPOVButton(joy2, 0);
    Button cargoShip = new JoystickPOVButton(joy2, 270);
    Button cargoIntake = new JoystickAxisButton(joy2, RobotMap.Controller.LT);
    Button cargoShoot = new JoystickAxisButton(joy2, RobotMap.Controller.RT);
    Button resetEncoder = new EnabledButton(joy2, RobotMap.Controller.RIGHT_FACE);

    public static Joystick joy4 = new Joystick(3);

    public OI() {
        slow.whileHeld(new SwerveSetLowSpeed());
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        runAuto.whenPressed(new PathTesting());
        visionFoward.whileHeld(new SwerveDriveVisionForwards());
        visionBack.whileHeld(new SwerveDriveVisionReverse());
        hatchExtend.toggleWhenPressed(new HatchPistonExtend());

        hatchIn.whenPressed(new HatchPistonGrab());
        hatchOut.whenPressed(new HatchPistonPlace());
        cargoIntake.whileHeld(new CargoIn());
        cargoShoot.whileHeld(new CargoOut());
        if (Robot.hatch.hatchExtended) {
            hatch1.whenPressed(new ElevatorHatch1());
        }
        else {
            hatch1.whenPressed(new ElevatorCargoGround());
        }
        hatch2.whenPressed(new ElevatorHatch2());
        hatch3.whenPressed(new ElevatorHatch3());
        cargo1.whenPressed(new ElevatorCargo1());
        cargo2.whenPressed(new ElevatorCargo2());
        cargo3.whenPressed(new ElevatorCargo3());
        cargoShip.whenPressed(new ElevatorCargoShip());
        resetEncoder.whenPressed(new ElevatorReset());
    }
}