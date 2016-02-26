package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.Position;
import edu.wpi.first.wpilibj.command.Command;


public class RunShooter extends Command {

	private double leftSpeed, rightSpeed, duration;

	protected void initialize() {
		Position pos = Robot.shooter.getCurrentPosition();
		leftSpeed = pos.leftSpeed;
		rightSpeed = pos.rightSpeed;
		duration = pos.runTime;
		setTimeout(duration);
	}

	protected void execute() {
		Robot.shooter.run(leftSpeed, rightSpeed);
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
