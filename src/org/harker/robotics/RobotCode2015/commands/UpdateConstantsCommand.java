package org.harker.robotics.RobotCode2015.commands;

import org.harker.robotics.RobotCode2015.Constants;
import org.harker.robotics.harkerrobolib.util.IOUtil;

/**
 * Updates the constants that the robot uses
 * @see Constants
 * @author neymikajain
 */
public class UpdateConstantsCommand extends CommandBase {

    public UpdateConstantsCommand() {
        super ("Update Constants Command");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Constants.updateConstants();
	IOUtil.println("Updating constants");
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
