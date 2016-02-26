package org.usfirst.frc.team1991.robot;

public enum Position {
	// Shooter
	SHOOTER_STOWED(2.600, 0.0, 0.0, 0.0, 0.0, 0.0),
	SHOOTER_CLOSE_SHOT(2.420, 0.6, 0.6, 0.8, 3, 1),
	SHOOTER_FAR_SHOT(1.978, 0.85, 0.85, 0.8, 3, 1),
	SHOOTER_BARF(1.225, 0.4, 0.4, 0.8, 3, 1),
	// Intake
	INTAKE_UP(1.284),
	INTAKE_DOWN(1.284);

	public final double setPoint;
	public final double leftSpeed;
	public final double rightSpeed;
	public final double feedSpeed;
	public final double runTime;
	public final double feedTime;

	Position(double setPoint, double leftSpeed, double rightSpeed, double feedSpeed, double runTime, double feedTime) {
		this.setPoint = setPoint;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.feedSpeed = feedSpeed;
		this.runTime = runTime;
		this.feedTime = feedTime;
	}

	Position(double setPoint) {
		this.setPoint = setPoint;
		this.leftSpeed = 0;
		this.rightSpeed = 0;
		this.feedSpeed = 0;
		this.runTime = 0;
		this.feedTime = 0;
	}
}
