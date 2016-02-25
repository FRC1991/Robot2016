package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class FeedShooter extends Command {
	
	double duration, speed;
	
    protected void initialize() {
    	this.duration = Robot.shooter.currentPosition.feedTime;
		this.speed = Robot.shooter.currentPosition.feedSpeed;
    	setTimeout(duration);
    }

    protected void execute() {
    	Robot.shooter.feed(speed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.shooter.stop();
    }

    protected void interrupted() {
    	Robot.shooter.stop();
    }
}
