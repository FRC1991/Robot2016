package src.autonomous;

import src.Robot;
import src.XCommand;

public class TurnToYaw extends XCommand {

	private double desiredYaw;
	
	public TurnToYaw(double desiredYaw) {
		this.desiredYaw = desiredYaw;
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		Robot.drivetrain.setYawAndSpeed(desiredYaw, 0);
		Robot.drivetrain.enable();
	}
	
	@Override
	protected void execute() {
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		return Robot.drivetrain.onTarget();
	}
}
