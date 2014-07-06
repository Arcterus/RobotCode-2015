package org.harker.robotics.RobotCode2015;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;

    public static class Drivetrain {

        //Motor Channels
        public static final int LEFT_BACK_MOTOR_CHANNEL = 1; //TODO: Get actual numbers
        public static final int LEFT_MIDDLE_MOTOR_CHANNEL = 5;
        public static final int LEFT_FRONT_MOTOR_CHANNEL = 2;
        public static final int RIGHT_BACK_MOTOR_CHANNEL = 3;
        public static final int RIGHT_MIDDLE_MOTOR_CHANNEL = 6;
        public static final int RIGHT_FRONT_MOTOR_CHANNEL = 4;

        //Encoder Channels
        public static final int LEFT_ENC_CHANNEL_A = 1;
        public static final int LEFT_ENC_CHANNEL_B = 2;
        public static final int RIGHT_ENC_CHANNEL_A = 3;
        public static final int RIGHT_ENC_CHANNEL_B = 4;

        //Gyro Channel
        public static final int GYRO_CHANNEL = 5;

        //Shifter Channel
        public static final int SHIFTER_CHANNEL = 7;
    }

    public static class Gamepad {
        /*
        For those unfamilar with the layout of a gamepad (i.e. you have lived
        under a rock for the past 10 years or so) it goes as follows:
                            (Y)
                        (X)     (B)
                            (A)
        */
        public static final int BUTTON_A = 2;
        public static final int BUTTON_B = 3;
        public static final int BUTTON_X = 1;
        public static final int BUTTON_Y = 4;
        public static final int BUTTON_SELECT = 9;
        public static final int BUTTON_START = 10;

        public static final int BUTTON_STICK_LEFT = 11;
        public static final int BUTTON_STICK_RIGHT = 12;

        public static final int BUTTON_BUMPER_LEFT = 5;
        public static final int BUTTON_BUMPER_RIGHT = 6;

        public static final int BUTTON_TRIGGER_LEFT = 7;
        public static final int BUTTON_TRIGGER_RIGHT = 8;

        public static final int AXIS_DPAD = 6; //This was found in Overkill's code. Should be tested.

        public static final int GAMEPAD1_PORT = 1;
        public static final int GAMEPAD2_PORT = 2;
    }
}
