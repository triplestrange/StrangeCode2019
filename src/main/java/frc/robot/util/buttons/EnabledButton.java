package frc.robot.util.buttons;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class EnabledButton extends Button {
    
    private GenericHID joystick;
    private int buttonNumber;
    private DriverStation ds;

    public EnabledButton(GenericHID joystick, int buttonNumber) {
        this.joystick = joystick;
        this.buttonNumber = buttonNumber;
        ds = DriverStation.getInstance();
    }
    
    public boolean get() {
        return joystick.getRawButton(buttonNumber) && ds.isEnabled();
    }
}