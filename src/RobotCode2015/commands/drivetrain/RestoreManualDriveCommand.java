package RobotCode2015.commands.drivetrain;

import RobotCode2015.OI;
import RobotCode2015.subsystems.Drivetrain;
import RobotCode2015.Constants;
import RobotCode2015.commands.CommandBase;

/**
 * Checks whether the drivetrain has finished automatic functions or the user has 
 * forced an override to resume manual drive and gives control back to the user accordingly.
 * @author Brian Chan
 */
public class RestoreManualDriveCommand extends CommandBase {
    
    private boolean hasReset;
    
    public RestoreManualDriveCommand() {
        super ("Restore Manual Drive Command");
    }
    
    public void start() {
        super.start();
        hasReset = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /**
     * Waits for the user to return the joysticks back to the center. Otherwise,
     * the program might think that because the joysticks are still being pressed down
     * because the user cannot react fast enough, thus accidentally overriding an
     * automatic command.
     */
    protected void execute() {
	if(checkJoyMagMinLessThan(Constants.Drivetrain.manualOverrideResetThreshold))
            hasReset = true;
    }

    /**
     * When finished the command returns control to the user
     * @return <code>true</code> if either the drivetrain is free or the user has forced an override (pushed down hard on the joysticks)
     */
    protected boolean isFinished() {
	return !drivetrain.isBusy() || (hasReset && checkJoyMagMaxGreaterThan(Constants.Drivetrain.manualOverrideThreshold));
    }

    // Called once after isFinished returns true
    protected void end() {
        super.end();
	drivetrain.setBusy(false);
	OI.manualDrive.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    /**
     * Checks if the magnitude of either joystick is greater than the given limit.
     * @param limit The limit for testing.
     * @return <code>true</code> if either joystick exceeds the limit.
     */
    private boolean checkJoyMagMaxGreaterThan(double limit) {
        return ((RobotCode2015.OI.gamepad1.getMagnitude() > limit) || (RobotCode2015.OI.gamepad2.getMagnitude() > limit));
    }
    
    /**
     * Checks if the magnitude of either joystick is less than the given limit.
     * @param limit The limit for testing.
     * @return <code>true</code> if either joystick is below the limit.
     */
    private boolean checkJoyMagMinLessThan(double limit) {
        return ((RobotCode2015.OI.gamepad1.getMagnitude() < limit) || (RobotCode2015.OI.gamepad2.getMagnitude() < limit));
    }
}
