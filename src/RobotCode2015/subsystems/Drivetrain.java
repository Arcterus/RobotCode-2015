package RobotCode2015.subsystems;

import RobotCode2015.Constants;
import RobotCode2015.OI;
import RobotCode2015.RobotMap;
import RobotCode2015.util.Vector3D;
import RobotCode2015.wrappers.TalonWrapper;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author atierno
 * @author Arcterus
 */
public class Drivetrain extends Subsystem {
    
    private final TalonWrapper leftBackMotor;
    private final TalonWrapper leftMiddleMotor;
    private final TalonWrapper leftFrontMotor;
    private final TalonWrapper rightBackMotor;
    private final TalonWrapper rightMiddleMotor;
    private final TalonWrapper rightFrontMotor;
    
    private final Encoder leftEnc;
    private final Encoder rightEnc;
    
    private final Gyro gyro;
    
    private final Solenoid shifter;

    private double leftTargetSpeed, rightTargetSpeed, leftCurrSpeed, rightCurrSpeed;
    
    private boolean isBusy;
    
    public Drivetrain () {
        leftBackMotor   = new TalonWrapper (RobotMap.Drivetrain.LEFT_BACK_MOTOR_CHANNEL, Constants.Drivetrain.LEFT_BACK_MOTOR_FLIPPED);
        leftMiddleMotor = new TalonWrapper (RobotMap.Drivetrain.LEFT_MIDDLE_MOTOR_CHANNEL, Constants.Drivetrain.LEFT_MIDDLE_MOTOR_FLIPPED);
        leftFrontMotor  = new TalonWrapper (RobotMap.Drivetrain.LEFT_FRONT_MOTOR_CHANNEL, Constants.Drivetrain.LEFT_FRONT_MOTOR_FLIPPED);
        rightBackMotor  = new TalonWrapper (RobotMap.Drivetrain.RIGHT_BACK_MOTOR_CHANNEL, Constants.Drivetrain.RIGHT_BACK_MOTOR_FLIPPED);
        rightMiddleMotor = new TalonWrapper (RobotMap.Drivetrain.RIGHT_MIDDLE_MOTOR_CHANNEL, Constants.Drivetrain.RIGHT_MIDDLE_MOTOR_FLIPPED);
        rightFrontMotor = new TalonWrapper (RobotMap.Drivetrain.RIGHT_FRONT_MOTOR_CHANNEL, Constants.Drivetrain.RIGHT_FRONT_MOTOR_FLIPPED);
        
        leftEnc  = new Encoder (RobotMap.Drivetrain.LEFT_ENC_CHANNEL_A, RobotMap.Drivetrain.LEFT_ENC_CHANNEL_B);
        rightEnc = new Encoder (RobotMap.Drivetrain.RIGHT_ENC_CHANNEL_A, RobotMap.Drivetrain.RIGHT_ENC_CHANNEL_B);
        
        gyro = new Gyro(RobotMap.Drivetrain.GYRO_CHANNEL);
        
        shifter = new Solenoid(RobotMap.Drivetrain.SHIFTER_CHANNEL);

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
        leftCurrSpeed = scale (leftCurrSpeed, leftTargetSpeed, Constants.Drivetrain.ACCELERATION_SCALING, Constants.Drivetrain.ACCELERATION_THRESHOLD);
        rightCurrSpeed = scale (rightCurrSpeed, rightTargetSpeed, Constants.Drivetrain.ACCELERATION_SCALING, Constants.Drivetrain.ACCELERATION_THRESHOLD);
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
        leftMiddleMotor.set(speed);
        leftFrontMotor.set(speed);
        rightBackMotor.set(speed);
        rightMiddleMotor.set(speed);
        rightFrontMotor.set(speed);
    }
    
    /**
     * Set the left and right motors to certain speeds. Do not use for regular driving!
     * @param left The left motor speed.
     * @param right The right motor speed.
     */
    public void driveRaw (double left, double right) {
        leftBackMotor.set(left);
        leftMiddleMotor.set(left);
        leftFrontMotor.set(left);
        rightBackMotor.set(right);
        rightMiddleMotor.set(right);
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
	if(offsetPosition.magnitude() < Constants.Drivetrain.DRIVE_TO_POINT_THRESHOLD) return true;

	//get our current velocity and calculate theta
	Vector3D currentVelocity = new Vector3D(
		getAverageRate() * Math.cos(getYawAngle()),
		getAverageRate() * Math.sin(getYawAngle()),
		0);
	double theta = offsetPosition.normalize().cross(currentVelocity.normalize()).getDirection();

	//drive based on theta and speed
	drive(speed + theta * Constants.Drivetrain.DRIVE_TO_POINT_SCALING, speed - theta * Constants.Drivetrain.DRIVE_TO_POINT_SCALING); //TODO: does this break with acceleration ACCELERATION_SCALING
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

    /**
     * Sets the robot into high gear.
     */
    public void setHighGearState() {
        shifter.set(Constants.Drivetrain.HIGH_GEAR_STATE);
    }

    /**
     * Sets the robot into low gear.
     */
    public void setLowGearState() {
        shifter.set(Constants.Drivetrain.LOW_GEAR_STATE);
    }

    /**
     * Toggles the robot's gear.
     */
    public void toggleGearState() {
        shifter.set(!shifter.get());
    }

    /**
     * Sets the gear of the robot.
     * @param state The state to set to.
     */
    public void setGearState(boolean state) {
        shifter.set(state);
    }

    /**
     * Gets the current gear of the robot.
     * @return The gear state.
     */
    public boolean getGearState() {
        return shifter.get();
    }
}
