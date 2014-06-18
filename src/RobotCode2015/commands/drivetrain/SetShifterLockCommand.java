/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.drivetrain;

import RobotCode2015.commands.CommandBase;

/**
 * This command sets the state of the soft lock on the gear shifter to force the robot
 * to remain in one gear regardless of the automatic shifting code.
 * @author atierno
 */
public class SetShifterLockCommand extends CommandBase {
    private final boolean isLocked;

    /**
     * Default constructor. Assumes that the caller wants to toggle the state of the shifter.
     */
    public SetShifterLockCommand() {
        super("Set Shifter Lock Command");
        isLocked = !drivetrain.isShifterLocked();
    }

    public SetShifterLockCommand(boolean state) {
        super("Set Shifter Lock Command");
        isLocked = state;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drivetrain.setShifterLocked(isLocked);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
