package RobotCode2015;

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
        public static final int leftBackMotorChannel = 1; //TODO: Get actual numbers
        public static final int leftFrontMotorChannel = 2;
        public static final int rightBackMotorChannel = 3;
        public static final int rightFrontMotorChannel = 4;
        
        public static final int leftEncChannelA = 1;
        public static final int leftEncChannelB = 2;
        public static final int rightEncChannelA = 3;
        public static final int rightEncChannelB = 4;
        
        public static final int gyroChannel = 5;
    }
}
