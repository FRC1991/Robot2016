package src.teleop;

import edu.wpi.first.wpilibj.command.Command;
import src.Robot;


public class Drive extends Command {
	private XboxController driver;

	public Drive() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		driver = Robot.driver;
	}

	protected void execute() {
		double leftSpeed, rightSpeed, leftTrigger, rightTrigger;
		 leftSpeed = driver.getLJoystickY();
		 rightSpeed = driver.getRJoystickY();
		/* ARCADE DRIVE
		 	double speed = driver.getLJoystickY() * 0.7;
			double turn = driver.getLJoystickX();
			leftSpeed = speed + turn;
			rightSpeed = speed - turn;
		*/
		// Handle rotation
		if (driver.areTriggersPressed()) {
			leftTrigger = driver.getLTrigger();
			rightTrigger = driver.getRTrigger();
			if (leftTrigger > 0) {
				leftSpeed = -leftTrigger;
				rightSpeed = -leftSpeed;
			}
			else {
				rightSpeed = -rightTrigger;
				leftSpeed = -rightSpeed;
			}
			leftSpeed *= 0.5;
			rightSpeed *= 0.5;
		}
		Robot.drivetrain.drive(leftSpeed, rightSpeed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivetrain.disable();
	}

	protected void interrupted() {
		Robot.drivetrain.disable();
	}
}
