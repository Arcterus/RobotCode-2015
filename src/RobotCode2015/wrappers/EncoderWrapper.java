package RobotCode2015.wrappers;

import RobotCode2015.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 *
 * @author Brian Chan
 */
public class EncoderWrapper extends Encoder {

    private final double rateScale;
    private double rate, acceleration; //Rate and acceleration should be in meters per second
    //previousCallTime denotes the last time updateRate() was called to be used for acceleration code (See below)
    private double previousCallTime = System.currentTimeMillis() / 1000;

    public EncoderWrapper(int aChannel, int bChannel) {
	super(aChannel, bChannel);
	rate = 0;
        acceleration = 0;
	this.rateScale = 1;
    }

    public EncoderWrapper(int aChannel, int bChannel, double rateScale) {
	super(aChannel, bChannel);
	rate = 0;
        acceleration = 0;
	this.rateScale = rateScale;
    }

    public EncoderWrapper(int aSource, int bSource, final EncodingType encodingType) {
	super(aSource, bSource, false, encodingType);
	rate = 0;
        acceleration = 0;
	this.rateScale = 1;
    }
    
    public EncoderWrapper(int aSource, int bSource, final EncodingType encodingType, double rateScale) {
	super(aSource, bSource, false, encodingType);
	rate = 0;
        acceleration = 0;
	this.rateScale = rateScale;
    }

    public double getRate() {
	return rate * rateScale;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void updateRate() {
	double curRate = super.getRate();
        double currTime;
	if(!Double.isNaN(curRate))
	    rate += (curRate - rate) / Constants.Drivetrain.ENCODER_RATE_SCALING;
        currTime = System.currentTimeMillis() / 1000; //Convert to seconds
        updateAcceleration(currTime - previousCallTime, curRate, rate);
        previousCallTime = currTime; //Update timestep
    }

    /**
     * Finds acceleration as the change in velocity over the change in time
     * @param timeStep The time between the current and previous call of the function.
     * @param oldRate The previous rate of the encoder.
     * @param newRate The current rate of the encoder.
     */
    public void updateAcceleration(double timeStep, double oldRate, double newRate) {
        acceleration = (newRate - oldRate) / timeStep;
    }
}
