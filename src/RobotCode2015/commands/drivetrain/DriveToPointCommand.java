package RobotCode2015.commands.drivetrain;
import RobotCode2015.util.MathUtil;
import RobotCode2015.commands.CommandBase;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author neymikajain
 */
public class DriveToPointCommand extends AutomaticDriveCommand {
    
    public double x, y, angle, speed;
    public double distance;
    public double start;
    public long startTime;
    public boolean isCommandFinished;
    
    public DriveToPointCommand(double x, double y, double speed) 
    {
        requires(drivetrain);
        this.x = x;
        this.y = y;
        angle = MathUtil.aTan((x/y));
        this.distance = 0;
        start = drivetrain.getAverageDistance();

        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	startTime = System.currentTimeMillis();
	isCommandFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {       
        isCommandFinished = drivetrain.driveToPoint(x, y, speed, System.currentTimeMillis() - startTime);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        if(((drivetrain.getAverageDistance() - start) >= distance) &&  drivetrain.getYawAngle() == angle)
            return true; 
        return isCommandFinished;
    }
}
