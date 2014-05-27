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
 */
public class Constants {
    
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
        
        public static double accelerationThreshold, accelerationScaling;
        public static double leftEncDistPerPulse, rightEncDistPerPulse;
        public static double lowSpeedScaling, highSpeedScaling;
        public static double encoderRateScaling;
        public static double manualOverrideResetThreshold, manualOverrideThreshold;
        public static double turnToAngleError;
        public static double driveToPointScaling, driveToPointThreshold;
        public static boolean leftBackMotorFlipped, leftFrontMotorFlipped, rightBackMotorFlipped, rightFrontMotorFlipped;  
        
        private static void updateConstants () { //TODO: Test for proper values
            
            //Acceleration thresholds and scale factor for driving.
            accelerationThreshold = prefs.getDouble("dt_accelerationThreshold", .2); 
            accelerationScaling = prefs.getDouble("dt_accelerationScaling", .1);
            
            // -----------------------------
	    //	Encoder Distance per Pulse:
	    //	    experimental values: ticks per cycle * distance moved (manually) / encoder pulses
	    // -----------------------------
            leftEncDistPerPulse = prefs.getDouble("dt_leftEncDistPerPulse", 1 * 2 / 3);
            rightEncDistPerPulse = prefs.getDouble("dt_rightEncDistPerPulse", 1 * 2 / 3);
            
            //Scaling inputs from the gamepad (high speed and low speed).
            lowSpeedScaling = prefs.getDouble("dt_lowSpeedScaling", .5);
            highSpeedScaling = prefs.getDouble("dt_highSpeedScaling", 1);
            
            //Scaling for the encoders.
            encoderRateScaling = prefs.getDouble("dt_encoderRateScaling", 2);
            
            //Thresholds before returning control of drivetrain back to the user.
            manualOverrideResetThreshold = prefs.getDouble("dt_manualOverrideResetThreshold", .1);
            manualOverrideThreshold = prefs.getDouble("dt_manualOverrideThreshold", .5);
            
            //The accepted error when attempting to turn to a given angle.
            turnToAngleError = prefs.getDouble("dt_turnToAngleError", Math.PI / 12);
            
            //Scale factor for the drive to point command and distance threshold for offset.
            driveToPointScaling = prefs.getDouble("dt_driveToPointScaling", .1);
            driveToPointThreshold = prefs.getDouble("dt_driveToPointThreshold", .1);
                    
            //Whether or not to flip the directions of the motors.
            leftBackMotorFlipped = prefs.getBoolean("dt_leftBackFlipped", false);
            leftFrontMotorFlipped = prefs.getBoolean("dt_leftFrontFlipped", false);
            rightBackMotorFlipped = prefs.getBoolean("dt_rightBackFlipped", false);
            rightFrontMotorFlipped = prefs.getBoolean("dt_rightFrontFlipped", false);
        }
    }
}
