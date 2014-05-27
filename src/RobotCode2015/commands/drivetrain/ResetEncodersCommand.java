
package RobotCode2015.commands.drivetrain;

import RobotCode2015.commands.CommandBase;

/**
 * Resets the encoders back to zero
 * @author Manan
 */
public class ResetEncodersCommand extends CommandBase {
    
    public ResetEncodersCommand() {
        super ("Reset Encoders Command");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivetrain.getLeftEnc().reset();
        drivetrain.getRightEnc().reset();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        super.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
