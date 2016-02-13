package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class FeedShooter extends Command {

	double duration;
	
	public FeedShooter(double duration) {
		this.duration = duration;
	}
    protected void initialize() {
    	setTimeout(duration);
    }

    protected void execute() {
    	Robot.shooter.feed();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
