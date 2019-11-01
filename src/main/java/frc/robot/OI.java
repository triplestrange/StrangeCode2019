package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.util.buttons.*;
import frc.robot.RobotMap;
import frc.robot.RobotMap.Elevator;
import frc.robot.commands.elevator.ElevatorMM;
import frc.robot.commands.elevator.ElevatorReset;
import frc.robot.commands.hatch.HatchPistonExtend;
import frc.robot.commands.hatch.HatchPistonGrab;
import frc.robot.commands.hatch.HatchPistonPlace;
import frc.robot.commands.swerve.*;

public class OI {
    public static Joystick joy1 = new Joystick(0);
    // Button lockSwerve = new EnabledButton(joy1, RobotMap.Controller.LEFT);
    Button fieldOrient = new EnabledButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new EnabledButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    Button slow = new EnabledButton(joy1, RobotMap.Controller.JOY_RIGHT);
    Button hatchGrab = new JoystickAxisButton(joy1, RobotMap.Controller.LT);
    Button hatchPlace = new JoystickAxisButton(joy1, RobotMap.Controller.RT);
    Button hatchExtend = new EnabledButton(joy1, RobotMap.Controller.X);
    Button hatch1 = new EnabledButton(joy1, RobotMap.Controller.A);
    Button hatch2 = new EnabledButton(joy1, RobotMap.Controller.B);
    Button hatch3 = new EnabledButton(joy1, RobotMap.Controller.Y);
    Button resetEncoder = new EnabledButton(joy1, RobotMap.Controller.RIGHT_FACE);

    public OI() {
        slow.whileHeld(new SwerveSetLowSpeed());
        // lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());
        hatchGrab.whileHeld(new HatchPistonGrab());
        hatchPlace.whileHeld(new HatchPistonPlace());
        hatchExtend.toggleWhenPressed(new HatchPistonExtend());
        hatch1.whenPressed(new ElevatorMM(Elevator.HATCH_1ROCKET));
        hatch2.whenPressed(new ElevatorMM(Elevator.HATCH_2ROCKET));
        hatch3.whenPressed(new ElevatorMM(Elevator.HATCH_3ROCKET));
        resetEncoder.whenPressed(new ElevatorReset());
    }
}