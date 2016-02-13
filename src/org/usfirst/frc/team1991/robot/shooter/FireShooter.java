package org.usfirst.frc.team1991.robot.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FireShooter extends CommandGroup {
    
    public FireShooter() {
    	addParallel(new RunShooter(0.8, 0.8, 3));
    	addSequential(new WaitCommand(1));
    	addSequential(new FeedShooter(1));
    }
}
