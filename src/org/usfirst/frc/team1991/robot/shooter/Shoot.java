package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Shoot extends CommandGroup {

    public Shoot() {
      requires(Robot.shooter);
      setTimeout(5); // At most it should take 5 seconds to fire
    	addParallel(new RunShooter());
    	addSequential(new WaitForShooterSpeed());
    	addSequential(new FeedShooter());
    }

    @Override
    protected boolean isFinished() {
      if (!super.isFinished()) {
        return isTimedOut();
      }
      return true;
    }
}
