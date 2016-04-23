package src.teleop;

import src.Robot;
import src.XCommand;

public class Feed extends XCommand {

	public Feed() {
		requires(Robot.shooter);
		requires(Robot.intake);
	}

	protected void execute() {
		if (!Robot.shooter.ballPresent()) {
			Robot.intake.feed(1);
			Robot.shooter.feed(0.5);
		}
		else {
			Robot.intake.feed(0);
			Robot.shooter.feed(0);
		}
	}

	protected void quit(boolean wasInterrupted) {
		Robot.intake.disable();
		Robot.shooter.disable();
	}
}
