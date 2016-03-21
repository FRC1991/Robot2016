package src.teleop;

import src.Robot;
import src.XCommand;

public class StraightDrive extends XCommand {

	private double desiredYaw;
	
	public StraightDrive() {
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		this.desiredYaw = Robot.drivetrain.getPosition();
		Robot.drivetrain.setYawAndSpeed(desiredYaw, 0);
		Robot.drivetrain.enable();
	}
	
	@Override
	protected void execute() {
		Robot.drivetrain.setYawAndSpeed(desiredYaw, Robot.driver.getLJoystickY());
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		return false;
	}
}
