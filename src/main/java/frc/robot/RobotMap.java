package frc.robot;

public class RobotMap {
    public static double acc = 50;
    public static double vCruise = 60;
    public static int DEFAULT_TIMEOUT = 30; // milliseconds

    public static class SwerveDrive {
        // These numbers control the Swerve PID
        public static final double SWERVE_STEER_P = 0.75;
        public static final double SWERVE_STEER_I = 0;
        public static final double SWERVE_STEER_D = 0.075;
        public static final double SWERVE_STEER_CAP = 1;

        // Port which the drive motors are plugged into
        public static final int FL_DRIVE = 1;
        public static final int FR_DRIVE = 2;
        public static final int BL_DRIVE = 3;
        public static final int BR_DRIVE = 4;

        // Port which the steering motors are plugged into
        public static final int FL_STEER = 5;
        public static final int FR_STEER = 6;
        public static final int BL_STEER = 7;
        public static final int BR_STEER = 8;

        // Port which the steering encoders are plugged into
        public static final int FL_ENCODER = 0;
        public static final int FR_ENCODER = 1;
        public static final int BL_ENCODER = 2;
        public static final int BR_ENCODER = 3;
        
        //Encoder offsets for real robot
        public final static double FL_ENC_OFFSET = 212.2;
        public final static double FR_ENC_OFFSET = 173.4;
        public final static double BL_ENC_OFFSET = 284.4;
        public final static double BR_ENC_OFFSET = 269.3;

        // public final static double FL_ENC_OFFSET = 275.5+324;
        // public final static double FR_ENC_OFFSET = 165;
        // public final static double BL_ENC_OFFSET = 142;
        // public final static double BR_ENC_OFFSET = 290;

        public static final boolean ENCODERS_REVERSED = false;

        // Cad Wheel Base information for Aries
        public static final double WHEEL_BASE_LENGTH = 19.25;
        public static final double WHEEL_BASE_WIDTH = 27.254;

        public static final double SPEED = 90;
        public static final double TURN_RATE = 90;
    }

    public static class Controller {
        public final static int A = 1;
        public final static int B = 2;
        public final static int X = 3;
        public final static int Y = 4;
        public final static int LEFT_BUMPER = 5;
        public final static int RIGHT_BUMPER = 6;
        public final static int LEFT_FACE = 7;
        public final static int RIGHT_FACE = 8;
        public final static int JOY_LEFT = 9;
        public final static int JOY_RIGHT = 10;

        public final static int LX = 0;
        public final static int LY = 1;
        public final static int LT = 2;
        public final static int RT = 3;
        public final static int RX = 4;
        public final static int RY = 5;

        public final static int UP = 0;
        public final static int RIGHT = 90;
        public final static int DOWN = 180;
        public final static int LEFT = 270;

    }


    public static class Elevator {
		public final static int elevator1 = 9; // CAN ID
        public final static int elevator2 = 10; // CAN ID

        public final static double PEAK_LIMIT = 15; // Amps
        public final static double PEAK_DURATION = 500; // milliseconds
        public final static double CONTINUOUS_LIMIT = 10; // Amps
        
        public static int START_POSITION = 17038;
        public final static int CARGO_1ROCKET = 16380;
        public final static int CARGO_2ROCKET = 38844;
        public final static int CARGO_3ROCKET = 63424;
        public final static int CARGO_SHIP = 25000;
        public final static int HATCH_1ROCKET = 3000;
        public final static int HATCH_2ROCKET = 28731;
        public final static int HATCH_3ROCKET = 54186;
    }
    
    public static class Hatch {
        public final static int hatchMotor = 11;
        public final static int INTAKE_PISTON = 2;
        public final static int EXTEND = 4;
        public final static int RETRACT = 3;
    }

    public static class Cargo {
        public final static int L_MOTOR = 21;
        public final static int R_MOTOR = 22;
        public final static int MOTOR = 23;
    }
}