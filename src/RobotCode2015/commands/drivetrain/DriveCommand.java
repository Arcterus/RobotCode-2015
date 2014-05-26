
package RobotCode2015.commands.drivetrain;


/**
 *
 * @author farzin
 */
public class DriveCommand extends AutomaticDriveCommand {

    private double right, left;

    public DriveCommand(double left, double right) {
	requires(drivetrain);
	this.right = right;
	this.left = left;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	drivetrain.drive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return true;
    }
    
}
