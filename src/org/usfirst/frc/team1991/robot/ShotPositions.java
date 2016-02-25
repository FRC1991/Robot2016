package org.usfirst.frc.team1991.robot;

// Feeder parameters for each type of shot
// Note that for now there is always a one second delay between starting the runners and feeding the ball
public enum ShotPositions {
	//Shooter
	STOWED(2.600, 0.0, 0.0, 0.0, 0.0, 0.0), 
	CLOSE_SHOT(2.420, 0.6, 0.6, 0.8, 3, 1), 
	FAR_SHOT(1.978, 0.85, 0.85, 0.8, 3, 1), 
	BARF(1.225, 0.4, 0.4, 0.8, 3, 1),
	
	
	//Intake
	UP(1.284, 0.0, 0.0, 0.0, 0.0, 0.0),
	DOWN(1.284, 0.0, 0.0, 0.0, 0.0, 0.0);
	
	public final double setPoint;
	public final double leftSpeed;
	public final double rightSpeed;
	public final double feedSpeed;
	public final double runTime;
	public final double feedTime;
	
	ShotPositions(double setPoint, double leftSpeed, double rightSpeed, double feedSpeed, double runTime, double feedTime) {
		this.setPoint = setPoint;
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.feedSpeed = feedSpeed;
		this.runTime = runTime;
		this.feedTime = feedTime;
	}
}
