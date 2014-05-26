/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.drivetrain;

import RobotCode2015.OI;
import RobotCode2015.commands.CommandBase;

/**
 *
 * @author Manan
 */
public abstract class AutomaticDriveCommand extends CommandBase {
    
    // Called once after isFinished returns true
    protected void end() {
	OI.manualDriveRestore.start();
        drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
