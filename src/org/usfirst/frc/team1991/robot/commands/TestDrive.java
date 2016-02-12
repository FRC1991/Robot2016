package org.usfirst.frc.team1991.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TestDrive extends CommandGroup {
    
    public TestDrive() {
        addSequential(new AutoDrive(0.5, 0.5, 3));
        addSequential(new WaitCommand(3));
        addSequential(new AutoDrive(0.2, 0.2, 1));
    }
}
