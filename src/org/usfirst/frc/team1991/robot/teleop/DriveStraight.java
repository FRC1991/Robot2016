package org.usfirst.frc.team1991.robot.teleop;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	private XboxController driver;
	private double initialYaw;
	private double currentYaw;
	private boolean turn = false;
	private boolean autonomous = false;
	private boolean reverse = false;
	private double duration = 0.0;
	private double tolerance = 0.5;


	public DriveStraight(double duration, boolean autonomous, boolean reverse) {
		requires(Robot.drivetrain);
		Robot.drivetrain.resetNavigation();
		this.autonomous = autonomous;
		this.reverse = reverse;
		this.duration = duration;
		if (duration > 0) {
			setTimeout(duration);
		}

	}

	public DriveStraight(boolean turn, double currentYaw) {
		requires(Robot.drivetrain);
		Robot.drivetrain.resetNavigation();
		this.turn = turn;
		this.currentYaw = currentYaw;
	}

	protected void initialize() {
		driver = Robot.driver;
		initialYaw = Robot.drivetrain.getPosition();
		Robot.drivetrain.enable();
	}

	protected void execute() {
		double speed = driver.getRJoystickY();
		if (turn) {
			speed = 0;
			initialYaw = currentYaw;
		} else if (autonomous) {
			speed = 1.0;
		}
		if (reverse) {
			speed = -speed;
		}
		Robot.drivetrain.setYawAndSpeed(initialYaw, speed);
	}

	protected boolean isFinished() {
		if (turn) {
			return Robot.drivetrain.onTarget();
		} else if (duration > 0) {
			return isTimedOut();
		} else {
			return false;
		}
	}

	protected void end() {
		Robot.drivetrain.setYawAndSpeed(initialYaw, 0);
		Robot.drivetrain.disable();
		Robot.drivetrain.resetNavigation();
		reverse = false;
		turn = false;
		autonomous = false;
		duration = 0;
	}

	protected void interrupted() {
		Robot.drivetrain.disable();
	}


}
