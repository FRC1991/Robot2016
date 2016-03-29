package src.teleop;

import src.Robot;
import src.XCommand;

public class MoveClimberManually extends XCommand {

	private double speed, deadband = 0;
	
	public MoveClimberManually() {
		requires(Robot.climber);
	}
	
	@Override
	protected void execute() {
		speed = Robot.aux.getRTrigger() - Robot.aux.getLTrigger();
//		if (speed > deadband) {
//			Robot.climber.move(speed);
//		}
//		else {
//			Robot.climber.move(0);
//		}
		Robot.climber.move(speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void quit(boolean wasInterrupted) {
		Robot.climber.disable();
	}

}
