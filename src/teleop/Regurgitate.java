package src.teleop;

import src.Robot;
import src.XCommand;

public class Regurgitate extends XCommand {

	public Regurgitate() {
		requires(Robot.shooter);
	}
	
	protected void execute() {
		Robot.shooter.feed(-0.6);
	}

	protected void quit(boolean wasInterrupted) {
		Robot.shooter.disable();
	}
}
