/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.drivetrain;

import RobotCode2015.Constants;
import RobotCode2015.commands.CommandBase;

/**
 *
 * @author atierno
 */
public class AutomaticShiftingCommand extends CommandBase {

    private double[] samples = new double[10]; //Take multiple samples to reduce chance of outliers resulting in sudden switches
    int sampleIndex = 0; //Index to iterate over sample array
    double avgSpeed; //The average speed collected over time

    public AutomaticShiftingCommand() {
        super("Automatic Shifting Command");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        samples[sampleIndex] = CommandBase.drivetrain.getAverageRate();
        if (sampleIndex == samples.length - 1) { //If sufficient samples have been taken
            avgSpeed = average(samples);
            if (avgSpeed > Constants.Drivetrain.HIGH_GEAR_THRESHOLD)
                CommandBase.drivetrain.setHighGearState();
            if (avgSpeed < Constants.Drivetrain.LOW_GEAR_THRESHOLD)
                CommandBase.drivetrain.setLowGearState();
        }
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
