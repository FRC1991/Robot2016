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
		Robot.drivetrain.setYawAndSpeed(Robot.drivetrain.getPosition(), speed, false);
		Robot.drivetrain.enable();
		System.out.println("Driving in a straight line for " + timeout + " seconds at " + speed + " speed.");
	}
	
	@Override
	protected void execute() {
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		return isTimedOut();
	}
}