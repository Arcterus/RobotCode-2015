package org.harker.robotics.RobotCode2015.commands;

import org.harker.robotics.RobotCode2015.OI;
import org.harker.robotics.RobotCode2015.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    public static Drivetrain drivetrain;

    public double timerLength; //The duration of the timer
    public double startTime; //The time at which the Timer is initialized.

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        drivetrain = new Drivetrain();

        // Show what command your subsystem is running on the SmartDashboard
    }

    public CommandBase(String name) {
        super(name);
        timerLength = -1;
    }

    public void start() {
        super.start();
        System.out.println("Started " + this.getName());
        startTime = Timer.getFPGATimestamp(); //The system's time clock in seconds.
    }

    /**
     * Sets the length of the Timer for the Command.
     * @param length The new duration of the timer.
     */
    public void setTimerLength(double length) {
        timerLength = length;
    }

    /**
     * Checks if the Command's timer is finished.
     * @return true if the timer has completed.
     */
    public boolean isTimerFinished() {
        return (timerLength >= 0) && (Timer.getFPGATimestamp() - startTime >= timerLength);
    }

    /**
     * Resets the timer back to zero (i.e. makes the start time the current time).
     */
    public void resetTimer() {
        startTime = Timer.getFPGATimestamp();
    }

    protected void end() {
        System.out.println("Finished " + this.getName());
    }
}
