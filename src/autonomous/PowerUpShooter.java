package src.autonomous;

import src.Robot;
import src.XCommand;

public class PowerUpShooter extends XCommand {
	
	private double timeout = 2;
	
	public PowerUpShooter() {	
		requires(Robot.shooter);
	}
	
	protected void initialize() {
		Robot.shooter.disable();
		setTimeout(timeout);
	}

	@Override
	protected void execute() {
		Robot.shooter.runShooterMotorsWithRPM(150);
	}

	protected boolean isFinished() {
		return isTimedOut(); // Robot.shooter.shooterMotorsAtSpeed() || isTimedOut();
	}
	
}