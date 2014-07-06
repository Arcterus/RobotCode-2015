package org.harker.robotics.RobotCode2015.commands.drivetrain;

import org.harker.robotics.RobotCode2015.Constants;

/**
 * Turns the robot to a given angle
 * @author Benjamin
 */
public class TurnToAngleCommand extends AutomaticDriveCommand {
    private final double target;

    // Turns at a speed proportional to the distance from the target point
    // until it's closer than the error threshold.
    // The error threshold is actually in Constants.
    private static final double P = 1.0;

    /**
     * Initializes a new command
     * @param radiansToTurn The angle to turn to in radians
     */
    public TurnToAngleCommand(double radiansToTurn) {
	super ("Turn to Angle Command");
        requires(drivetrain);
	target = drivetrain.getYawAngle() + radiansToTurn;
    }

    public void start() {
	super.start();
	drivetrain.resetGyro();

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	double currentAngle = drivetrain.getYawAngle();
        // Check these directions?

        // TODO does this part work as intended?
        // Should set the drive coefficients to speed P if angle is 1 rad
        double speedIdeal = (currentAngle - target) * P;
        double realSpeed = Math.min(speedIdeal, 1);
	drivetrain.drive(-1 * realSpeed, realSpeed);


    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	double currentAngle = drivetrain.getYawAngle();
	return Math.abs(target - currentAngle) < Constants.Drivetrain.TURN_TO_ANGLE_ERROR;
    }
}
