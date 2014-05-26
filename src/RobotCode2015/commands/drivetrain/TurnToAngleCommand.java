/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.drivetrain;

import RobotCode2015.subsystems.Drivetrain;
import RobotCode2015.Constants;

/**
 *
 * @author Benjamin
 */
public class TurnToAngleCommand extends AutomaticDriveCommand {
    private double target;
    
    // Turns at a speed proportional to the distance from the target point
    // until it's closer than the error threshold.
    // The error threshold is actually in Constants.
    private static final double P = 1.0;

    public TurnToAngleCommand(double radiansToTurn) {
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
	return Math.abs(target - currentAngle) < Constants.Drivetrain.turnToAngleError;
    }
}
