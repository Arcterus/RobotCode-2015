package RobotCode2015.wrappers;

import RobotCode2015.Constants;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 *
 * @author Brian Chan
 * @version
 */
public class EncoderWrapper extends Encoder {

    private final double rateScale;
    private double rate;

    public EncoderWrapper(int aChannel, int bChannel) {
	super(aChannel, bChannel);
	rate = 0;
	this.rateScale = 1;
    }

    public EncoderWrapper(int aChannel, int bChannel, double rateScale) {
	super(aChannel, bChannel);
	rate = 0;
	this.rateScale = rateScale;
    }

    public EncoderWrapper(int aSource, int bSource, final EncodingType encodingType) {
	super(aSource, bSource, false, encodingType);
	rate = 0;
	this.rateScale = 1;
    }
    
    public EncoderWrapper(int aSource, int bSource, final EncodingType encodingType, double rateScale) {
	super(aSource, bSource, false, encodingType);
	rate = 0;
	this.rateScale = rateScale;
    }

    public double getRate() {
	return rate * rateScale;
    }

    public void updateRate() {
	double curRate = super.getRate();
	if(!Double.isNaN(curRate))
	    rate += (curRate - rate) / Constants.Drivetrain.encoderRateScaling;
    }

}
