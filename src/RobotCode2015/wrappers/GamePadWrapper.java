
package RobotCode2015.wrappers;

import RobotCode2015.RobotMap;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A Joystick wrapper for gamepads that include more accurate/useful names for the analogue stick axes
 * @author neymikajain
 */
public class GamePadWrapper extends Joystick {
    private final JoystickButtonWrapper buttonA;
    private final JoystickButtonWrapper buttonB;
    private final JoystickButtonWrapper buttonX;
    private final JoystickButtonWrapper buttonY;
    private final JoystickButtonWrapper buttonStart;
    private final JoystickButtonWrapper buttonSelect;
    private final JoystickButtonWrapper buttonStickLeft;
    private final JoystickButtonWrapper buttonStickRight;
    private final JoystickButtonWrapper buttonBumperLeft;
    private final JoystickButtonWrapper buttonBumperRight;
    private final JoystickButtonWrapper buttonTriggerLeft;
    private final JoystickButtonWrapper buttonTriggerRight;

    
    public GamePadWrapper(int port) {
	super(port);
        buttonA = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_A);
        buttonB = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_B);
        buttonX = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_X);
        buttonY = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_Y);
        buttonStart = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_START);
        buttonSelect = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_SELECT);
        buttonStickLeft = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_STICK_LEFT);
        buttonStickRight = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_STICK_RIGHT);
        buttonBumperLeft = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_BUMPER_LEFT);
        buttonBumperRight = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_BUMPER_RIGHT);
        buttonTriggerLeft = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_TRIGGER_LEFT);
        buttonTriggerRight = new JoystickButtonWrapper(this, RobotMap.Gamepad.BUTTON_TRIGGER_RIGHT);
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
    
    /**
     * Gets whether or not Button A is pressed
     * @return The state of the button
     */
    public boolean getButtonAState() {
        return buttonA.get();
    }
    
    /**
     * Gets whether or not Button B is pressed
     * @return The state of the button
     */
    public boolean getButtonBState() {
        return buttonB.get();
    }
    
    /**
     * Gets whether or not Button X is pressed
     * @return The state of the button
     */
    public boolean getButtonXState() {
        return buttonX.get();
    }
    
    /**
     * Gets whether or not Button Y is pressed
     * @return The state of the button
     */
    public boolean getButtonYState() {
        return buttonY.get();
    }
    
    /**
     * Gets whether or not the Start Button is pressed
     * @return The state of the button
     */
    public boolean getButtonStartState() {
        return buttonStart.get();
    }
    
    /**
     * Gets whether or not the Select Button is pressed
     * @return The state of the button
     */
    public boolean getButtonSelectState() {
        return buttonSelect.get();
    }
    
    /**
     * Gets whether or not the Left Stick Button is pressed
     * @return The state of the button
     */
    public boolean getButtonStickLeftState() {
        return buttonStickLeft.get();
    }
    
    /**
     * Gets whether or not the Right Stick Button is pressed
     * @return The state of the button
     */
    public boolean getButtonStickRightState() {
        return buttonStickRight.get();
    }
    
    /**
     * Gets whether or not the Left Bumper is pressed
     * @return The state of the button
     */
    public boolean getButtonBumperLeftState() {
        return buttonBumperLeft.get();
    }
    
    /**
     * Gets whether or not the Right Bumper is pressed
     * @return The state of the button
     */
    public boolean getButtonBumperRightState() {
        return buttonBumperRight.get();
    }
    
    /**
     * Gets whether or not the Left Trigger is pressed
     * @return The state of the button
     */
    public boolean getButtonTriggerLeftState() {
        return buttonTriggerLeft.get();
    }
    
    /**
     * Gets whether or not the Right Trigger is pressed
     * @return The state of the button
     */
    public boolean getButtonTriggerRightState() {
        return buttonTriggerRight.get();
    }
    
    /**
     * Gets whether or not the Right Button on the DPad is pressed. Since the DPad
     * is an axis, the code checks to see if the button is pushed significantly far enough
     * to the right.
     * @return The state of the button
     */
    public boolean getDPadRightState() {
        return this.getRawAxis(RobotMap.Gamepad.AXIS_DPAD) > .5;
    }
    
    /**
     * Gets whether or not the Right Button on the DPad is pressed. Since the DPad
     * is an axis, the code checks to see if the button is pushed significantly far enough
     * to the left.
     * @return The state of the button
     */
    public boolean getDPadLeftState() {
        return this.getRawAxis(RobotMap.Gamepad.AXIS_DPAD) < -.5;
    }
    
    /**
     * Gets an instance of Button A
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonA() {
        return buttonA;
    }
    
    /**
     * Gets an instance of Button B
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonB() {
        return buttonB;
    }
    
    /**
     * Gets an instance of Button X
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonX() {
        return buttonX;
    }
    
    /**
     * Gets an instance of Button Y
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonY() {
        return buttonY;
    }
    
    /**
     * Gets an instance of the Start Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonStart() {
        return buttonStart;
    }
    
    /**
     * Gets an instance of the Select Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonSelect() {
        return buttonSelect;
    }
    
    /**
     * Gets an instance of the Left Stick Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonStickLeft() {
        return buttonStickLeft;
    }
    
    /**
     * Gets an instance of the Right Stick Button
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonStickRight() {
        return buttonStickRight;
    }
    
    /**
     * Gets an instance of the Left Bumper
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonBumperLeft() {
        return buttonBumperLeft;
    }
    
    /**
     * Gets an instance of the Right Bumper
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonBumperRight() {
        return buttonBumperRight;
    }
    
    /**
     * Gets an instance of the Left Trigger
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonTriggerLeft() {
        return buttonTriggerLeft;
    }
    
    /**
     * Gets an instance of the Right Trigger
     * @return An instance of the button
     */
    public JoystickButtonWrapper getButtonTriggerRight() {
        return buttonTriggerRight;
    }
}
