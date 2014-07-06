package org.harker.robotics.RobotCode2015.commands.drivetrain;

import org.harker.robotics.RobotCode2015.Constants;
import org.harker.robotics.RobotCode2015.commands.CommandBase;

/**
 * Handles automatically shifting from low to high gear based on the average acceleration of the robot.
 * @author atierno
 */
public class AutomaticShiftingCommand extends CommandBase {

    private final double[] samples = new double[10]; //Take multiple samples to reduce chance of outliers resulting in sudden switches
    int sampleIndex = 0; //Index to iterate over sample array
    double avgAcceleration; //The average acceleration collected over time

    public AutomaticShiftingCommand() {
        super("Automatic Shifting Command");
        setTimerLength(0); //Allow the robot to initially shift instantly
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        samples[sampleIndex] = CommandBase.drivetrain.getAverageAcceleration();
        if (sampleIndex == samples.length - 1) { //If sufficient samples have been taken
            avgAcceleration = average(samples);
            //Check if either threshold is met, if there has been sufficient delay since the last gear shift, and if the gear is unlocked
            if (avgAcceleration > Constants.Drivetrain.HIGH_GEAR_THRESHOLD && isTimerFinished() && !drivetrain.isShifterLocked()) {
                CommandBase.drivetrain.setHighGearState();
                resetTimer();
                setTimerLength(Constants.Drivetrain.SHIFT_DELAY);
            }
            if (avgAcceleration < Constants.Drivetrain.LOW_GEAR_THRESHOLD && isTimerFinished() && !drivetrain.isShifterLocked()) {
                CommandBase.drivetrain.setLowGearState();
                resetTimer();
                setTimerLength(Constants.Drivetrain.SHIFT_DELAY);
            }
        }
        sampleIndex = (sampleIndex + 1) % samples.length; //Modulus covers for wrapping around from the end of the list to the beginning
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    private double average(double[] data) {
        if (data.length == 0)
            throw new java.lang.RuntimeException("Error: Tried to take average of an empty array!");
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return sum / data.length;
    }
}
