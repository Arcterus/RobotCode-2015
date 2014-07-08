package org.harker.robotics.RobotCode2015.commands.drivetrain;

import org.harker.robotics.RobotCode2015.Constants;
import org.harker.robotics.RobotCode2015.OI;
import org.harker.robotics.RobotCode2015.commands.CommandBase;
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;

/**
 * Takes the user's inputs from joysticks for driving.
 * @aauthor neymikajain
 */
public class ManualDriveCommand extends CommandBase {

    public ManualDriveCommand() {
        super ("Manual Drive Command");
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        GamepadWrapper gamepad = OI.gamepad1;
        if (gamepad.getRawButton(7)) { //Modifier for slow driving
            drivetrain.drive(gamepad.getLeftY() * Constants.Drivetrain.LOW_SPEED_SCALING,
                    gamepad.getRightY() * Constants.Drivetrain.LOW_SPEED_SCALING);
        } else {
            drivetrain.drive(gamepad.getLeftY() * Constants.Drivetrain.HIGH_SPEED_SCALING,
                    gamepad.getRightY() * Constants.Drivetrain.HIGH_SPEED_SCALING);
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drivetrain.isBusy();
    }

    // Called once after isFinished returns true
    protected void end() {
        super.end();
        drivetrain.setBusy(true);
        //starts the manualDriveRestore method - continually checks for joystick updates
        OI.manualDriveRestore.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
