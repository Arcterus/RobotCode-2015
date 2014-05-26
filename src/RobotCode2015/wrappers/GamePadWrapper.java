
package RobotCode2015.wrappers;

import edu.wpi.first.wpilibj.Joystick;

/**
 * A Joystick wrapper for gamepads that include more accurate/useful names for the analogue stick axes
 * @author neymikajain
 */
public class GamePadWrapper extends Joystick {

    public GamePadWrapper(int port) {
	super(port);
    }

    public double getLeftX() {
	return super.getX();
    }

    public double getLeftY() {
	return -super.getY(); //by default, forward returns a negative number, which is unintuitive
    }

    public double getRightX() {
	return super.getZ();
    }

    public double getRightY() {
	return -super.getThrottle(); //by default, forward returns a negative number, which is unintuitive
    }
}
