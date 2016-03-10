package org.usfirst.frc.team1991.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class SwegSystem extends Subsystem {
	private double setpoint, tolerance, upSpeed, downSpeed, minPosition, maxPosition;
	private boolean enabled;

	public SwegSystem(double upSpeed, double downSpeed, double minPosition, double maxPosition, double tolerance) {
		this.upSpeed = upSpeed;
		this.downSpeed = downSpeed;
		this.minPosition = minPosition;
		this.maxPosition = maxPosition;
		this.tolerance = tolerance;
	}

	public void periodic() {
		// Only do anything if the system is enabled and the position is within the range
		if (enabled && ( getCurrentPosition() > minPosition) && ( getCurrentPosition() < maxPosition) ) {
			double output = 0, error = getError();
			if (error > 0) {
				output = upSpeed;
			}
			else if (error < 0) {
				output = downSpeed;
			}
			if (onPoint()) {
				output = 0;
				disable();
			}
			else {
				useOutput(output);
			}
		}
	}

	public abstract double getCurrentPosition();

	public abstract void useOutput(double output);

	public boolean onPoint() {
		return (Math.abs(getError()) < tolerance);
	}
	public boolean isEnabled() {
		return enabled;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public double getSetpoint() {
		return setpoint;
	}

	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}

	public double getError() {
		return setpoint - getCurrentPosition();
	}

	public void initDefaultCommand() {}
}
