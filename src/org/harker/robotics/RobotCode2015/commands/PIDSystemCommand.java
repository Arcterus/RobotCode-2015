package org.harker.robotics.RobotCode2015.commands;

import org.harker.robotics.harkerrobolib.util.PIDSystem;

/**
 * A command that relies on the use of a PID Feedback Loop for correction or fine tuning.
 * @author atierno
 */
public abstract class PIDSystemCommand extends CommandBase {

    private final PIDSystem system;
    private double timeSinceLastCall;

    /**
     * A new PIDSystemCommand with no saturation checks.
     * @param name The name of the {@link PIDSystem}.
     * @param p The proportional gain.
     * @param i The integral gain.
     * @param d The derivative gain.
     */
    public PIDSystemCommand(String name, double p, double i, double d) {
        super("PID System Command");
        system = new PIDSystem(name, p, i, d);
        timeSinceLastCall = System.currentTimeMillis();
    }

    /**
     * A new PIDSystemCommand with checks for saturation.
     * @param name The name of the {@link PIDSystem}.
     * @param p The proportional gain.
     * @param i The integral gain.
     * @param d The derivative gain.
     * @param saturationLow The minimum output of the integral term of the {@link PIDSystem} (see reference).
     * @param saturationHigh The maximum output of the integral term of the {@link PIDSystem} (see reference.
     * @see PIDSystem#updatePID(double, double)
     */
    public PIDSystemCommand(String name, double p, double i, double d, double saturationLow, double saturationHigh) {
        super("PID System Command");
        system = new PIDSystem(name, p, i, d, saturationLow, saturationHigh);
        timeSinceLastCall = System.currentTimeMillis();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double currTime = System.currentTimeMillis();
        system.updatePID(startTime, currTime - timeSinceLastCall);
        timeSinceLastCall = currTime;
        useOutput(system.getOutput());
    }

    /**
     * This function must be overridden by any PIDSystemCommand. It takes the
     * result of {@link PIDSystem#getOutput()} to apply to the desired output
     * source (such as a Talon for instance).
     * @param output The output of the internal {@link PIDSystem}.
     */
    protected abstract void useOutput(double output);

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
}
