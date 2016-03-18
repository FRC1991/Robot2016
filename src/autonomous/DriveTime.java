package src.autonomous;

import src.Robot;
import src.XCommand;

public class DriveTime extends XCommand {

	private double timeout, speed;
	
	public DriveTime(double timeout, double speed) {
		this.timeout = timeout;
		this.speed = speed;
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		setTimeout(timeout);
		Robot.drivetrain.setYawAndSpeed(Robot.drivetrain.getPosition(), speed);
		Robot.drivetrain.enable();
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.driveUsingPID();
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		return isTimedOut();
	}
}