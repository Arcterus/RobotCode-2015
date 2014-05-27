/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands.misc;
import RobotCode2015.commands.CommandBase;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to run another command if a given button is not pressed.
 * @author Manan
 */
public class RunWithoutModifier extends CommandBase {

    private final Command command;
    private final Button button;
    
    public RunWithoutModifier(Button button, Command command) {
        super ("Run without Modifer Command");
        this.button = button;
        this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!button.get()) command.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !command.isRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
        super.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        command.cancel();
    }
}
