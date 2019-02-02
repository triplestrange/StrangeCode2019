/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.PathCommand;
import frc.robot.commands.auto.PathTesting;
import frc.robot.commands.swerve.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    Joystick joy1 = new Joystick(0);
    Button lowSpeed = new JoystickButton(joy1, RobotMap.Controller.LEFT_TRIGGER);
    Button highSpeed = new JoystickButton(joy1, RobotMap.Controller.RIGHT_TRIGGER);
    Button lockSwerve = new JoystickButton(joy1, RobotMap.Controller.A);
    Button fieldOrient = new JoystickButton(joy1, RobotMap.Controller.RIGHT_BUMPER);
    Button gyroReset = new JoystickButton(joy1, RobotMap.Controller.LEFT_BUMPER);
    Button runAuto = new JoystickButton(joy1, RobotMap.Controller.B);

    public OI() {
        lowSpeed.whileHeld(new SwerveSetLowSpeed());
        highSpeed.whileHeld(new SwerveSetHighSpeed());
        lockSwerve.whenPressed(new SwerveLock());
        fieldOrient.whenPressed(new SwerveSetField());
        gyroReset.whenPressed(new SwerveGyroReset());

        runAuto.whenPressed(new PathTesting());
    }
}