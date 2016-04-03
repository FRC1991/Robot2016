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
		System.out.println("Turning to yaw: " + desiredYaw);
		Robot.drivetrain.setYawAndSpeed(desiredYaw, 0);
		Robot.drivetrain.enable();
	}
	
	@Override
	protected void execute() {
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	public boolean isFinished() {
		if (Robot.drivetrain.onTarget()) {
			System.out.println("Reached yaw of " + desiredYaw + " - Error: " + Robot.drivetrain.getError());
		}
		return Robot.drivetrain.onTarget();
	}
}
