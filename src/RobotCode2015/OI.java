
package RobotCode2015;

import RobotCode2015.commands.drivetrain.ManualDriveCommand;
import RobotCode2015.commands.drivetrain.RestoreManualDriveCommand;
import RobotCode2015.wrappers.GamePadWrapper;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static RestoreManualDriveCommand manualDriveRestore = new RestoreManualDriveCommand();
    public static ManualDriveCommand manualDrive = new ManualDriveCommand();
    
    public static GamePadWrapper gamepad1;
    public static GamePadWrapper gamepad2;
    
    public static Joystick joy1;
    public static Joystick joy2;
}

