/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.drivetrain;

import RobotCode2015.commands.CommandBase;

/**
 * Manually toggles the gear of the robot (this should be used only if automatic shifting fails.)
 * @author atierno
 */
public class ManualShiftGearCommand extends CommandBase {
    private final boolean state;

    /**
     * Default constructor, will set the gear to the opposite of what it is now
     */
    public ManualShiftGearCommand() {
        super("Manual Shift Gear Command");
        state = !CommandBase.drivetrain.getGearState();
    }

    /**
     * Set the gear to a desired state
     * @param state The state to set the robot to
     */
    public ManualShiftGearCommand(boolean state) {
        super("Manual Shift Gear Command");
        this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.drivetrain.setGearState(state);
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
