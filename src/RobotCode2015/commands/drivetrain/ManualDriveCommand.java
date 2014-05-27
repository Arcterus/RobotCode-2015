/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.drivetrain;

import RobotCode2015.Constants;
import RobotCode2015.OI;
import RobotCode2015.util.IOUtil;
import RobotCode2015.commands.CommandBase;
import RobotCode2015.wrappers.GamePadWrapper;

/**
 * Takes the user's inputs from joysticks for driving.
 * @author neymikajain
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
        GamePadWrapper gamepad = RobotCode2015.OI.gamepad1;
        if (gamepad.getRawButton(7)) { //Modifier for slow driving
            drivetrain.drive(gamepad.getLeftY() * Constants.Drivetrain.lowSpeedScaling,
                    gamepad.getRightY() * Constants.Drivetrain.lowSpeedScaling);
        } else {
            drivetrain.drive(gamepad.getLeftY() * Constants.Drivetrain.highSpeedScaling,
                    gamepad.getRightY() * Constants.Drivetrain.highSpeedScaling);
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
