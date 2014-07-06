package org.harker.robotics.RobotCode2015;

import RobotCode2015.commands.drivetrain.DriveStraightCommand;
import RobotCode2015.commands.drivetrain.ManualDriveCommand;
import RobotCode2015.commands.drivetrain.ManualShiftGearCommand;
import RobotCode2015.commands.drivetrain.RestoreManualDriveCommand;
import RobotCode2015.commands.drivetrain.SetShifterLockCommand;
import org.harker.robotics.harkerrobolib.wrappers.GamepadWrapper;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public static RestoreManualDriveCommand manualDriveRestore = new RestoreManualDriveCommand();
    public static ManualDriveCommand manualDrive = new ManualDriveCommand();

    public static GamepadWrapper gamepad1;
    public static GamepadWrapper gamepad2;

    public static Joystick joy1;
    public static Joystick joy2;

    public OI () {
        gamepad1 = new GamepadWrapper(RobotMap.Gamepad.GAMEPAD1_PORT);
        gamepad1.getButtonA().whenPressed(new DriveStraightCommand(.5, 3));
        gamepad1.getButtonX().whenPressed(new SetShifterLockCommand());
        gamepad1.getButtonB().whenPressed(new ManualShiftGearCommand());
    }
}

