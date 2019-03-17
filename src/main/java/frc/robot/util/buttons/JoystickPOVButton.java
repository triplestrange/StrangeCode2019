package frc.robot.util.buttons;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;


public class JoystickPOVButton extends Button {

    private Joystick joystick;
    private int desiredPOV;
    private DriverStation ds;

    public JoystickPOVButton(Joystick stick, int newDesiredPOV) {
        joystick = stick;
        desiredPOV = newDesiredPOV;
        ds = DriverStation.getInstance();
    }

    public boolean get() {
        return (joystick.getPOV() == desiredPOV) && ds.isEnabled();
    }
}