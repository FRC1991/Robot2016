package src.teleop;

import src.Robot;
import src.XCommand;

public class CancelDrivetrainCommands extends XCommand {
	
	public CancelDrivetrainCommands() {
		requires(Robot.drivetrain);
	}
	
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void execute() {		
	}
}
