package frc.robot;

public class RobotMap {
    public static double acc = 50;
    public static double vCruise = 60;

    public static class SwerveDrive {
        // These numbers control the Swerve PID
        public static final double SWERVE_STEER_P = 2;
        public static final double SWERVE_STEER_I = 0;
        public static final double SWERVE_STEER_D = 0;
        public static final double SWERVE_STEER_CAP = 1;

        // Port which the drive motors are plugged into
        public static final int BR_DRIVE = 21;
        public static final int FR_DRIVE = 22;
        public static final int FL_DRIVE = 23;
        public static final int BL_DRIVE = 24;

        // Port which the steering motors are plugged into
        public static final int FL_STEER = 11;
        public static final int FR_STEER = 2;
        public static final int BL_STEER = 12;
        public static final int BR_STEER = 1;

        // Port which the steering encoders are plugged into
        public static final int FL_ENCODER = 2;
        public static final int FR_ENCODER = 1;
        public static final int BR_ENCODER = 0;
        public static final int BL_ENCODER = 3;

        // Offset of the encoders to correct the orientation of installation
        // Encoder offsets for ???
        // public final static double FL_ENC_OFFSET = 0;
        // public final static double FR_ENC_OFFSET = 0;
        // public final static double BL_ENC_OFFSET = 0;
        // public final static double BR_ENC_OFFSET = 0;

        public final static double FL_ENC_OFFSET = 73;
        public final static double FR_ENC_OFFSET = 324;
        public final static double BL_ENC_OFFSET = 190;
        public final static double BR_ENC_OFFSET = 294.5;

        // Whether or not the encoders are reversed
        public static final boolean ENCODERS_REVERSED = false;

        // Cad Wheel Base information for ???
        // public static final double WHEEL_BASE_WIDTH = 15.255;
        // public static final double WHEEL_BASE_LENGTH = 26.828;

        // Cad Wheel Base information for Croton
        public static final double WHEEL_BASE_WIDTH = 21.25;
        public static final double WHEEL_BASE_LENGTH = 26.25;

    }

    public static class Controller {
        public final static int X = 1;
        public final static int A = 2;
        public final static int B = 3;
        public final static int Y = 4;
        public final static int LEFT_BUMPER = 5;
        public final static int RIGHT_BUMPER = 6;
        public final static int LEFT_TRIGGER = 7;
        public final static int RIGHT_TRIGGER = 8;
        public final static int BACK = 9;
        public final static int START = 10;
    }
}