package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.Position;
import edu.wpi.first.wpilibj.command.Command;


public class FeedShooter extends Command {

	double duration, speed;

	protected void initialize() {
		Position pos = Robot.shooter.getCurrentPosition();
		duration = pos.feedTime;
		speed = pos.feedSpeed;
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
