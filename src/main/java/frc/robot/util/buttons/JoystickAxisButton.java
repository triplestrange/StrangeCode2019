package frc.robot.util.buttons;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickAxisButton extends Button {
    public static final int BOTH_WAYS = 1;
    public static final int POSITIVE_ONLY = 2;
    public static final int NEGATIVE_ONLY = 3;

    private static final double AXIS_THRESHOLD = 0.1;

    private Joystick joystick;
    private int axisInt;
    private int direction;
    private DriverStation ds;

    public JoystickAxisButton(Joystick stick, int axis) {
        this(stick, axis, BOTH_WAYS);
    }

    public JoystickAxisButton(Joystick stick, int axis, int direction) {
        joystick = stick;
        axisInt = axis;
        this.direction = direction;
        ds = DriverStation.getInstance();
    }

    public boolean get() {
        switch (direction) {
            case BOTH_WAYS:
                return (Math.abs(joystick.getRawAxis(axisInt)) > AXIS_THRESHOLD) && ds.isEnabled();

            case POSITIVE_ONLY:
                return (joystick.getRawAxis(axisInt) > AXIS_THRESHOLD) && ds.isEnabled();

            case NEGATIVE_ONLY:
                return (joystick.getRawAxis(axisInt) < -AXIS_THRESHOLD) && ds.isEnabled();
            }
        return false;
    }
}