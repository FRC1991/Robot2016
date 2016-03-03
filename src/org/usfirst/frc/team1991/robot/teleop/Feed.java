package org.usfirst.frc.team1991.robot.teleop;

import org.usfirst.frc.team1991.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Feed extends Command {

	protected void initialize() {
	}

	protected void execute() {
		if (!Robot.shooter.ballPresent()) {
			Robot.intake.feed(0.7);
			Robot.shooter.feed(0.35);
		}
		else {
			Robot.intake.feed(0);
			Robot.shooter.feed(0);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.intake.disable();
		Robot.shooter.disable();
	}

	protected void interrupted() {
		Robot.intake.disable();
		Robot.shooter.disable();
	}
}
