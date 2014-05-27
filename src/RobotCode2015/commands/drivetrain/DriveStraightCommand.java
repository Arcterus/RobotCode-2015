package RobotCode2015.commands.drivetrain;

import RobotCode2015.subsystems.Drivetrain;
import RobotCode2015.Constants;

/**
 *
 * @author Manan
 * @author Arcterus
 */
public class DriveStraightCommand extends AutomaticDriveCommand {
	private double speed;

	private double leftStart, rightStart;

	private double left, right;

	public DriveStraightCommand() {
		requires(drivetrain);
	}

	protected void initialize() { }

	public void start() {
		super.start();
		speed = .5;
		left = speed;
		right = speed;

		leftStart = drivetrain.getLeftDistance();
		rightStart = drivetrain.getRightDistance();

		drivetrain.driveRaw(speed, speed);
	}

	// Called repeatedly when this Command is scheduled to run
	// XXX: this may or may not work.  We need to test it.
	protected void execute() {
		double ratio = drivetrain.getLeftRate() / drivetrain.getRightRate();

		if(Double.isNaN(ratio)) ratio = 1.0;
		// 	drivetrain.driveRaw(speed / ratio, speed * ratio);

		//double difference = leftEnc.getDistance()-leftStart - rightEnc.getDistance()+rightStart; // d = l-r
		// double difference = (leftEnc.getDistance()-leftStart) - (.5*rightEnc.getDistance()-rightStart); // d = l-r

		right *= ratio;
		//left -= difference * Constants.DrivetrainConst.driveStraightScaling;
		//right += difference * Constants.DrivetrainConst.driveStraightScaling;

		// SmartDashboard.putNumber("diff",difference);
		// SmartDashboard.putNumber("scaled diff",difference*Constants.DrivetrainConst.driveStraightScaling);
		//
		// SmartDashboard.putNumber("Left Rate", leftEnc.getRate());
		// SmartDashboard.putNumber("Right Rate", rightEnc.getRate());
		drivetrain.driveRaw(left, right);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
}
