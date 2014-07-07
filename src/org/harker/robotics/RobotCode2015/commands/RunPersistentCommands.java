package org.harker.robotics.RobotCode2015.commands;

import org.harker.robotics.RobotCode2015.commands.drivetrain.AutomaticShiftingCommand;
import org.harker.robotics.RobotCode2015.commands.drivetrain.DrivetrainAccelerationCommand;
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
