package src.teleop;

import src.Robot;
import src.XCommand;

public class SetDriveBackwards extends XCommand {
	
	private boolean shouldDriveBackward, shouldToggle;
	
	public SetDriveBackwards() {
		shouldToggle = true;
		shouldDriveBackward = (Robot.drivetrain.isReversed() ^ true);
	}
	
	public SetDriveBackwards(boolean shouldDriveBackward) {
		shouldToggle = false;
		this.shouldDriveBackward = shouldDriveBackward;
	}
	
	protected void execute() {
		if (shouldToggle) {
			Robot.drivetrain.setReverse(Robot.drivetrain.isReversed() ^ true);
		}
		else {
			Robot.drivetrain.setReverse(shouldDriveBackward);
		}
		finish();
	}
}
