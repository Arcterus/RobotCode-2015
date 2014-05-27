package RobotCode2015.subsystems;

import RobotCode2015.Constants;
import RobotCode2015.OI;
import RobotCode2015.RobotMap;
import RobotCode2015.util.Vector3D;
import RobotCode2015.wrappers.TalonWrapper;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author atierno
 * @author Arcterus
 */
public class Drivetrain extends Subsystem {
    
    private TalonWrapper leftBackMotor;
    private TalonWrapper leftFrontMotor;
    private TalonWrapper rightBackMotor;
    private TalonWrapper rightFrontMotor;
    
    private Encoder leftEnc;
    private Encoder rightEnc;
    
    private Gyro gyro;
    
    private double leftTargetSpeed, rightTargetSpeed, leftCurrSpeed, rightCurrSpeed;
    
    private boolean isBusy;
    
    public Drivetrain () {
        leftBackMotor   = new TalonWrapper (RobotMap.Drivetrain.leftBackMotorChannel, Constants.Drivetrain.leftBackMotorFlipped);
        leftFrontMotor  = new TalonWrapper (RobotMap.Drivetrain.leftFrontMotorChannel, Constants.Drivetrain.leftFrontMotorFlipped);
        rightBackMotor  = new TalonWrapper (RobotMap.Drivetrain.rightBackMotorChannel, Constants.Drivetrain.rightBackMotorFlipped);
        rightFrontMotor = new TalonWrapper (RobotMap.Drivetrain.rightFrontMotorChannel, Constants.Drivetrain.rightFrontMotorFlipped);
        
        leftEnc  = new Encoder (RobotMap.Drivetrain.leftEncChannelA, RobotMap.Drivetrain.leftEncChannelB);
        rightEnc = new Encoder (RobotMap.Drivetrain.rightEncChannelA, RobotMap.Drivetrain.rightEncChannelB);
        
        gyro = new Gyro(RobotMap.Drivetrain.gyroChannel);
        
        leftTargetSpeed = rightTargetSpeed = leftCurrSpeed = rightCurrSpeed = 0;
        
        isBusy = false;
    }
    
    public void initDefaultCommand() {
        
        setDefaultCommand(OI.manualDrive);
    }
    
    /**
     * Sets the robot's target speed, which it will gradually arrive at with acceleration.
     * @param leftSpeed The left speed.
     * @param rightSpeed The right speed.
     */
    public void drive (double leftSpeed, double rightSpeed) {
        //Square the inputs but keep the signs.
        leftSpeed *= (leftSpeed > 0) ? leftSpeed : -leftSpeed;
        rightSpeed *= (rightSpeed > 0) ? rightSpeed : -rightSpeed;
        
        leftTargetSpeed = leftSpeed;
        rightTargetSpeed = rightSpeed;
    }
    
    /**
     * Moves the robot's speed closer to the target value.
     */
    public void updateDrive () {
        leftCurrSpeed = scale (leftCurrSpeed, leftTargetSpeed, Constants.Drivetrain.accelerationScaling, Constants.Drivetrain.accelerationThreshold);
        rightCurrSpeed = scale (rightCurrSpeed, rightTargetSpeed, Constants.Drivetrain.accelerationScaling, Constants.Drivetrain.accelerationThreshold);
        driveRaw (leftCurrSpeed, rightCurrSpeed);
    }

    /**
     * Update the rates of the Encoders.
     */
    public void updateEncoders() {
        /*leftEnc.updateRate();
        rightEnc.updateRate();*/
    }
    
    /**
     * Set all motors to one speed. Do not use for regular driving!
     * @param speed The speed to set the motors to.
     */
    public void driveRaw (double speed) {
        leftBackMotor.set(speed);
        leftFrontMotor.set(speed);
        rightBackMotor.set(speed);
        rightFrontMotor.set(speed);
    }
    
    /**
     * Set the left and right motors to certain speeds. Do not use for regular driving!
     * @param left The left motor speed.
     * @param right The right motor speed.
     */
    public void driveRaw (double left, double right) {
        leftBackMotor.set(left);
        leftFrontMotor.set(left);
        rightBackMotor.set(right);
        rightFrontMotor.set(right);
    }
    
    /**
     * Hard stop all motors. Use sparingly as this is very bad for the motors.
     */
    public void stop () {
        driveRaw (0);
    }
    
     /**
     * Drives the robot to a set point using vector arithmetic
     * @param x     Final x-position
     * @param y     Final y-position
     * @param speed the speed for the robot to move at
     * @param timeStep
     * @return true if success
     */
    public boolean driveToPoint(double x, double y, double speed, double timeStep) {
	//current position as a vector
	Vector3D currentPosition = new Vector3D(getAverageRate() * timeStep, getYawAngle());
	//where we want to go
	Vector3D desiredPosition = new Vector3D(x, y, 0);

	//how far the desired position is form the current position
	Vector3D offsetPosition = desiredPosition.subtract(currentPosition);

	//If the robot is close enough end command, break and return true
	if(offsetPosition.magnitude() < Constants.Drivetrain.driveToPointThreshold) return true;

	//get our current velocity and calculate theta
	Vector3D currentVelocity = new Vector3D(
		getAverageRate() * Math.cos(getYawAngle()),
		getAverageRate() * Math.sin(getYawAngle()),
		0);
	double theta = offsetPosition.normalize().cross(currentVelocity.normalize()).direction();

	//drive based on theta and speed
	drive(speed + theta * Constants.Drivetrain.driveToPointScaling, speed - theta * Constants.Drivetrain.driveToPointScaling); //TODO: does this break with acceleration accelerationScaling
	return false;
    }
    
    /**
     * Gets the left encoder rate.
     * @return The left encoder rate.
     */
    public double getLeftRate () {
        return leftEnc.getRate();
    }
    
    /**
     * Gets the right encoder rate.
     * @return The right encoder rate.
     */
    public double getRightRate () {
        return rightEnc.getRate();
    }
    
    /**
     * Gets the average rate of the robot.
     * @return The average rate.
     */
    public double getAverageRate () {
        return (leftEnc.getRate() + rightEnc.getRate()) / 2;
    }
    
    /**
     * Gets the distance the left encoder has traveled since the last reset.
     * @return The distance the left has traveled. 
     */
    public double getLeftDistance () {
        return leftEnc.getDistance();
    }
    
    /**
     * Gets the distance the right encoder has traveled since the last reset.
     * @return The distance the right has traveled.
     */
    public double getRightDistance () {
        return rightEnc.getDistance();
    }
    
    /**
     * Gets the average distance the robot has traveled since the last reset.
     * @return The average distance traveled.
     */
    public double getAverageDistance () {
        return (leftEnc.getDistance() + rightEnc.getDistance()) / 2;
    }

    /**
     * Gets the left Encoder.
     *
     * @return the left Encoder
     */
    public Encoder getLeftEnc() {
        return leftEnc;
    }

    /**
     * Gets the right Encoder.
     *
     * @return the right Encoder
     */
    public Encoder getRightEnc() {
        return rightEnc;
    }
    
    /**
     * Gets the angle of the robot's direction in radians.
     * @return The angle in radians
     */
    public double getYawAngle () {
        return gyro.getAngle() * Math.PI / 180; //Convert the angle to radians.
    }
    
    /**
     * Resets the robot's current heading to 0.
     */
    public void resetGyro () {
        gyro.reset();
    }
    
    /**
     * Weighted scaling for smooth acceleration.
     * @param curr The current speed
     * @param goal The target speed 
     * @param scale The scale factor for the weighted average
     * @param threshold The threshold for how close the current value must be to the target. 
     *  If this value is too great then acceleration will not run smoothly. However, if it is too small then the process will never be completed.
     * @return The scaled speed.
     */
    private double scale (double curr, double goal, double scale, double threshold) {
        return (Math.abs(goal - curr) < threshold ? 
                goal : goal * scale + curr * (1 - scale));
    }
    
    /**
     * Gets whether or not the drivetrain is busy.
     * @return The busy state of the drivetrain.
     */
    public boolean isBusy() {
        return isBusy;
    }
    
    /**
     * Sets the busy state of the drivetrain.
     * @param flag The state to set the busy status to.
     */
    public void setBusy(boolean flag) {
        isBusy = flag;
    }
}
