package src.teleop;

import edu.wpi.first.wpilibj.command.Command;
import src.Robot;

public class Feed extends Command {

	protected void initialize() {
	}

	protected void execute() {
		if (!Robot.shooter.ballPresent()) {
			Robot.intake.feed(1);
			Robot.shooter.feed(0.8);
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
