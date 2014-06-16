/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode2015;

import edu.wpi.first.wpilibj.Preferences;

/**
 *
 * @author atierno
 * @author Manan 
 */
public final class Constants {
    
    private Constants() {}
    
    private static Preferences prefs; 
    
    public static void updateConstants () {
        if (prefs == null)
            initializePreferences();
        Drivetrain.updateConstants();
    }
    
    private static void initializePreferences () {
        prefs = Preferences.getInstance();
    }
    
    public static class Drivetrain {
        
        public static double ACCELERATION_THRESHOLD, ACCELERATION_SCALING;
        public static double LEFT_ENC_DIST_PER_PULSE, RIGHT_ENC_DIST_PER_PULSE;
        public static double LOW_SPEED_SCALING, HIGH_SPEED_SCALING;
        public static double ENCODER_RATE_SCALING;
        public static double MANUAL_OVERRIDE_RESET_THRESHOLD, MANUAL_OVERRIDE_THRESHOLD;
        public static double TURN_TO_ANGLE_ERROR;
        public static double DRIVE_TO_POINT_SCALING, DRIVE_TO_POINT_THRESHOLD;
        public static boolean LEFT_BACK_MOTOR_FLIPPED, LEFT_MIDDLE_MOTOR_FLIPPED, LEFT_FRONT_MOTOR_FLIPPED, RIGHT_BACK_MOTOR_FLIPPED, RIGHT_MIDDLE_MOTOR_FLIPPED, RIGHT_FRONT_MOTOR_FLIPPED;  
        public static boolean HIGH_GEAR_STATE, LOW_GEAR_STATE;
        public static double HIGH_GEAR_THRESHOLD, LOW_GEAR_THRESHOLD;
        
        private static void updateConstants () { //TODO: Test for proper values
            
            //Acceleration thresholds and scale factor for driving.
            ACCELERATION_THRESHOLD = prefs.getDouble("dt_accelerationThreshold", .2); 
            ACCELERATION_SCALING = prefs.getDouble("dt_accelerationScaling", .1);
            
            // -----------------------------
	    //	Encoder Distance per Pulse:
	    //	    experimental values: ticks per cycle * distance moved (manually) / encoder pulses
	    // -----------------------------
            LEFT_ENC_DIST_PER_PULSE = prefs.getDouble("dt_leftEncDistPerPulse", 1 * 2 / 3);
            RIGHT_ENC_DIST_PER_PULSE = prefs.getDouble("dt_rightEncDistPerPulse", 1 * 2 / 3);
            
            //Scaling inputs from the gamepad (high speed and low speed).
            LOW_SPEED_SCALING = prefs.getDouble("dt_lowSpeedScaling", .5);
            HIGH_SPEED_SCALING = prefs.getDouble("dt_highSpeedScaling", 1);
            
            //Scaling for the encoders.
            ENCODER_RATE_SCALING = prefs.getDouble("dt_encoderRateScaling", 2);
            
            //Thresholds before returning control of drivetrain back to the user.
            MANUAL_OVERRIDE_RESET_THRESHOLD = prefs.getDouble("dt_manualOverrideResetThreshold", .1);
            MANUAL_OVERRIDE_THRESHOLD = prefs.getDouble("dt_manualOverrideThreshold", .5);
            
            //The accepted error when attempting to turn to a given angle.
            TURN_TO_ANGLE_ERROR = prefs.getDouble("dt_turnToAngleError", Math.PI / 12);
            
            //Scale factor for the drive to point command and distance threshold for offset.
            DRIVE_TO_POINT_SCALING = prefs.getDouble("dt_driveToPointScaling", .1);
            DRIVE_TO_POINT_THRESHOLD = prefs.getDouble("dt_driveToPointThreshold", .1);
                    
            //Whether or not to flip the directions of the motors.
            LEFT_BACK_MOTOR_FLIPPED = prefs.getBoolean("dt_leftBackFlipped", false);
            LEFT_MIDDLE_MOTOR_FLIPPED = prefs.getBoolean("dt_leftMiddleFlipped", false);
            LEFT_FRONT_MOTOR_FLIPPED = prefs.getBoolean("dt_leftFrontFlipped", false);
            RIGHT_BACK_MOTOR_FLIPPED = prefs.getBoolean("dt_rightBackFlipped", false);
            RIGHT_MIDDLE_MOTOR_FLIPPED = prefs.getBoolean("dt_rightMiddleFlipped", false);
            RIGHT_FRONT_MOTOR_FLIPPED = prefs.getBoolean("dt_rightFrontFlipped", false);

            //Booleans for the solenoid controlling gear shifting
            HIGH_GEAR_STATE = prefs.getBoolean("dt_highGearState", true);
            LOW_GEAR_STATE = prefs.getBoolean("dt_lowGearState", false);

            //Thresholds for when to switch gears (the difference between the two prevents constant switching around one value)
            HIGH_GEAR_THRESHOLD = prefs.getDouble("dt_highGearThreshold", .60);
            LOW_GEAR_THRESHOLD = prefs.getDouble("dt_lowGearThreshold", .40);
        }
    }
}
