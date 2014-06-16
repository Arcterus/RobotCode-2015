
package RobotCode2015;

import RobotCode2015.commands.drivetrain.DriveForTimeCommand;
import RobotCode2015.commands.drivetrain.DriveStraightCommand;
import RobotCode2015.commands.drivetrain.ManualDriveCommand;
import RobotCode2015.commands.drivetrain.RestoreManualDriveCommand;
import RobotCode2015.commands.drivetrain.ManualShiftGearCommand;
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
    
    public OI () {
        gamepad1 = new GamePadWrapper(RobotMap.Gamepad.GAMEPAD1_PORT);
        gamepad1.getButtonA().whenPressed(new DriveStraightCommand(.5, 3));
        gamepad1.getButtonX().whenPressed(new ManualShiftGearCommand());
        gamepad1.getButtonY().whenPressed(new ManualShiftGearCommand(Constants.Drivetrain.HIGH_GEAR_STATE));
        gamepad1.getButtonB().whenPressed(new ManualShiftGearCommand(Constants.Drivetrain.LOW_GEAR_STATE));
    }
}

