/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotCode2015.commands;

import RobotCode2015.commands.drivetrain.DrivetrainAccelerationCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author atierno
 */
public class RunPersistentCommands extends CommandGroup {
    
    public RunPersistentCommands() {
        addParallel(new DrivetrainAccelerationCommand());
    }
}
