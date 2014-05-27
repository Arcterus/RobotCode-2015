package RobotCode2015.commands.drivetrain;
import RobotCode2015.util.MathUtil;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Timer;

/**
 * Drives to a point (x,y) at a given speed.
 * @author neymikajain
 */
public class DriveToPointCommand extends AutomaticDriveCommand {
    
    public double x, y, angle, speed;
    public double distance;
    public double start;
    public boolean isCommandFinished;
    
    public DriveToPointCommand(double x, double y, double speed) {
        super ("Drive to Point Command");
        requires(drivetrain);
        this.x = x;
        this.y = y;
        angle = MathUtils.atan((x/y));
        this.distance = 0;
        start = drivetrain.getAverageDistance();

        this.speed = speed;
    }
    
    
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {       
        isCommandFinished = drivetrain.driveToPoint(x, y, speed, Timer.getFPGATimestamp() - startTime);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        if(((drivetrain.getAverageDistance() - start) >= distance) &&  drivetrain.getYawAngle() == angle) 
            return true; //This scenario is unrealistic, it returns true only if the robot is EXACTLY at the desired point. Might want to use a threshold?
        return isCommandFinished;
    }
}
