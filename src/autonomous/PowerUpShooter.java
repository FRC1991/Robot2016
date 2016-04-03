package src.autonomous;

import src.Robot;
import src.XCommand;

public class PowerUpShooter extends XCommand {
	
	private double timeout = 2;
	private double highGoalSpeed = 0.8;
	private double lowGoalSpeed = 0.6;
	private double lowGoalPosition = 3.6;
	private double speed;
	
	public PowerUpShooter() {	
		requires(Robot.shooter);
	}
	
	protected void initialize() {
		Robot.shooter.disable();
		setTimeout(timeout);
		double pos = Robot.shooter.getCurrentPosition();
		speed = highGoalSpeed;
		if (pos >= lowGoalPosition) {
			speed = lowGoalSpeed;
		}
		System.out.println("Running shooter with " + (speed == highGoalSpeed ? "high" : "low") + " goal speed.");
	}

	@Override
	protected void execute() {
		Robot.shooter.runShooterMotorsWithVoltage(speed);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}
	
}