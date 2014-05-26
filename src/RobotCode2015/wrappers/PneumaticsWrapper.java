/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotCode2015.wrappers;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Manan
 * takes in two solenoids and tells one to go forwards 
 * and the other to go forwards (when it is told to go forward)
 * 
 */
public class PneumaticsWrapper 
{
    private final Solenoid out, in;
    
    public PneumaticsWrapper(int outChannel, int inChannel) {
        this.out = new Solenoid(outChannel);
        this.in = new Solenoid(inChannel);
    }
    
    //extends
    public void set(boolean state) {
         out.set(!state); //everything is flipped because solenoids are always flipped
         in.set(state);
    }
    
    public boolean get() {
        return in.get();
    }
}
