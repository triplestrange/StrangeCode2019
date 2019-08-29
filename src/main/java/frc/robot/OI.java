package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.util.buttons.*;
import frc.robot.commands.auto.*;
import frc.robot.commands.cargo.*;
import frc.robot.commands.climb.*;
import frc.robot.commands.hatch.*;
import frc.robot.commands.swerve.*;
import frc.robot.commands.elevator.*;
import frc.robot.RobotMap;
import frc.robot.RobotMap.Elevator;

public class OI {
    public static Joystick joy1 = new Joystick(0);
    Button lockSwerve = new EnabledButton(joy1, RobotMap.Controller.A);
    Button fieldOrient = new EnabledButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new EnabledButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    Button visionFoward = new JoystickAxisButton(joy1, RobotMap.Controller.LT);
    Button visionBack = new JoystickAxisButton(joy1, RobotMap.Controller.RT);
    Button slow = new EnabledButton(joy1, RobotMap.Controller.JOY_RIGHT);
    Button shortPiston = new EnabledButton(joy1, RobotMap.Controller.LEFT_FACE);
    Button stopArm = new EnabledButton(joy1, RobotMap.Controller.RIGHT_FACE);
    Button runWheels = new EnabledButton(joy1, RobotMap.Controller.JOY_LEFT);
    
    // Button prepare2 = new EnabledButton(joy1, RobotMap.Controller.RIGHT_FACE);
    Button prepare3 = new EnabledButton(joy1, RobotMap.Controller.X);
    Button climb = new EnabledButton(joy1, RobotMap.Controller.Y);
    Button retract = new EnabledButton(joy1, RobotMap.Controller.B);

    
    public static Joystick joy2 = new Joystick(1);
    Button hatchGrab = new EnabledButton(joy2, RobotMap.Controller.LEFT_BUMPER);
    Button hatchPlace = new EnabledButton(joy2, RobotMap.Controller.RIGHT_BUMPER);
    Button hatchExtend = new EnabledButton(joy2, RobotMap.Controller.X);
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

    public OI() {
        slow.whileHeld(new SwerveSetLowSpeed());
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        visionFoward.whileHeld(new SwerveDriveVisionForwards());
        visionBack.whileHeld(new SwerveDriveVisionReverse());
        shortPiston.toggleWhenPressed(new CargoShortExtend());
        runWheels.whenActive(new RunWheels());
        stopArm.whileHeld(new ArmStop());

        prepare3.whenPressed(new ClimbPrepare3());
        climb.whenPressed(new Climb3());
        retract.whenPressed(new FinishClimb());

        hatchGrab.whenPressed(new HatchPistonGrab());
        hatchPlace.whenPressed(new HatchPistonPlace());
        // hatchExtend.toggleWhenPressed(command);
        cargoIntake.whileHeld(new CargoIn());
        cargoShoot.whileHeld(new CargoOut());
        if (Robot.hatch.hatchExtended) {
            hatch1.whenPressed(new ElevatorMM(Elevator.HATCH_1ROCKET));
        }
        else {
            hatch1.whenPressed(new ElevatorMM(0));
        }
        hatch2.whenPressed(new ElevatorMM(Elevator.HATCH_2ROCKET));
        hatch3.whenPressed(new ElevatorMM(Elevator.HATCH_3ROCKET));
        cargo1.whenPressed(new ElevatorMM(Elevator.CARGO_1ROCKET));
        cargo2.whenPressed(new ElevatorMM(Elevator.CARGO_2ROCKET));
        cargo3.whenPressed(new ElevatorMM(Elevator.CARGO_3ROCKET));
        cargoShip.whenPressed(new ElevatorMM(Elevator.CARGO_SHIP));
        resetEncoder.whenPressed(new ElevatorReset());
    }
}