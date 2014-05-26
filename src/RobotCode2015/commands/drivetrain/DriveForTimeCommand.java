/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.drivetrain;
import RobotCode2015.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Manan
 */
public class DriveForTimeCommand extends AutomaticDriveCommand {
    private final double left, right;
    private boolean isStopped;
    
    public DriveForTimeCommand(double left, double right, double timeout) {
        requires(drivetrain);
        this.right = right;
        this.left = left;
        isStopped = true;
	setTimerLength(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(isStopped) {
            System.out.println("just started");
            isStopped = false;
            startTime = Timer.getFPGATimestamp(); //hack to restart timer
        }
        
        drivetrain.drive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimerFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("finished driving");
        isStopped = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
