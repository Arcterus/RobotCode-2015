package org.harker.robotics.RobotCode2015.commands.drivetrain;

/**
 * Drive the robot at a given speed for a certain amount of time.
 * @author Manan
 */
public class DriveForTimeCommand extends AutomaticDriveCommand {
    private final double left, right;
    private boolean isStopped;

    public DriveForTimeCommand(double left, double right, double timeout) {
        super ("Drive for Time Command");
        requires(drivetrain);
        this.right = right;
        this.left = left;
        isStopped = true;
	setTimerLength(timeout);
    }

    //Called each time this command is started.
    public void start() {
        System.out.println("Started " + this.getName());
        isStopped = false;
        this.resetTimer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!isStopped)
            drivetrain.drive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimerFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
        super.end();
        System.out.println("Finished " + this.getName());
        isStopped = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
