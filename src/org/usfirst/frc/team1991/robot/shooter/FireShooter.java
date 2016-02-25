package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FireShooter extends CommandGroup {
    
    public FireShooter() {
    	addParallel(new RunShooter());
    	addSequential(new WaitCommand(1));
    	addSequential(new FeedShooter());
    }
}
