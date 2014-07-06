package org.harker.robotics.RobotCode2015.commands;

import RobotCode2015.commands.drivetrain.AutomaticShiftingCommand;
import RobotCode2015.commands.drivetrain.DrivetrainAccelerationCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author atierno
 */
public class RunPersistentCommands extends CommandGroup {

    public RunPersistentCommands() {
        addParallel(new DrivetrainAccelerationCommand());
        addParallel(new AutomaticShiftingCommand());
    }
}
