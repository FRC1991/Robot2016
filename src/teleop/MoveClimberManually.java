package src.teleop;

import src.Robot;
import src.XCommand;

public class MoveClimberManually extends XCommand {

	public MoveClimberManually() {
		requires(Robot.climber);
	}
	
	@Override
	protected void execute() {
		Robot.climber.move(Robot.aux.getRTrigger() - Robot.aux.getLTrigger());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void quit(boolean wasInterrupted) {
		Robot.climber.disable();
	}

}
