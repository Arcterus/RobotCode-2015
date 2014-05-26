package RobotCode2015.wrappers;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author 1072
 */
public class AnalogChannelButtonWrapper extends ButtonWrapper {

    private static double THRESHOLD = 0.5;
    private final int port;
    private AnalogChannel channel;

    public AnalogChannelButtonWrapper(int port) {
        this.port = port;
        channel = new AnalogChannel(port);
    }

    public boolean get() {
        channel.updateTable();
        return channel.getValue() > THRESHOLD;
    }
    
    public void setThreshold(double newVal) { 
        THRESHOLD = newVal; 
    }
    
    public double getThreshold() {
        return THRESHOLD;
    }
}
