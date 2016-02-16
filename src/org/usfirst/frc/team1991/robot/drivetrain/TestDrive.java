package org.usfirst.frc.team1991.robot.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TestDrive extends CommandGroup {

    public TestDrive() {
        addSequential(new Drive(0.5, 0.5, 3));
        addSequential(new Drive(1, 0, 1.3));
        addSequential(new Drive(0.6, 0.6, 1));
    }
}
