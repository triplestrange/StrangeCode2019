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
        public static final int FL_DRIVE = 1;
        public static final int FR_DRIVE = 2;
        public static final int BL_DRIVE = 3;
        public static final int BR_DRIVE = 4;

        // Port which the steering motors are plugged into
        public static final int FL_STEER = 11;
        public static final int FR_STEER = 2;
        public static final int BL_STEER = 12;
        public static final int BR_STEER = 1;

        // Port which the steering encoders are plugged into
        public static final int FL_ENCODER = 2;
        public static final int FR_ENCODER = 1;
        public static final int BL_ENCODER = 3;
        public static final int BR_ENCODER = 0;
        
        public final static double FL_ENC_OFFSET = 0;
        public final static double FR_ENC_OFFSET = 0;
        public final static double BL_ENC_OFFSET = 0;
        public final static double BR_ENC_OFFSET = 0;

        public static final boolean ENCODERS_REVERSED = false;

        // Cad Wheel Base information for ???
        public static final double WHEEL_BASE_WIDTH = 19.25;
        public static final double WHEEL_BASE_LENGTH = 27.254;
    }

    public static class Controller {
        public final static int A = 1;
        public final static int B = 2;
        public final static int X = 3;
        public final static int Y = 4;
        public final static int LEFT_BUMPER = 5;
        public final static int RIGHT_BUMPER = 6;
        public final static int LEFT_TRIGGER = 7;
        public final static int RIGHT_TRIGGER = 8;
        public final static int JOY_LEFT = 9;
        public final static int JOY_RIGHT = 10;
    }


    public static class Elevator {
		public final static int elevator1 = 8;
		public final static int elevator2 = 9;
    }
    
    public static class Hatch {
        public final static int hatchMotor = 9;
        public final static int hatchEncoderOffset = 10;  
        public final static int hatchEncoderPort = 4;

        public final static int hatch0Button = 0;
        public final static int hatch90Button = 90;
        public final static int hatch180Button = 180;

    }

    public static class Cargo {
        public final static int L_MOTOR = 9;
        public final static int R_MOTOR = 10;
        public final static int MOTOR = 11;
    }

}