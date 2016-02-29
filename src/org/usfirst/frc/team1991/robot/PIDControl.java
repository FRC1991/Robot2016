package org.usfirst.frc.team1991.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class PIDControl extends PIDCommand {
	protected AnalogInput encoder;
	protected SpeedController motor;
	public PIDController pid;
	protected PIDControllerConfiguration config;

	public PIDControl(PIDControllerConfiguration config, double setPoint, AnalogInput encoder, SpeedController motor) {
		super(0, 0, 0);
		this.pid = getPIDController();
		this.config = config;
		this.encoder = encoder;
		this.motor = motor;
		config.configurePIDController(pid);
		pid.setSetpoint(setPoint);
	}

	protected void initialize() {}

	protected void execute() {}

	// The usual getAverageTolerance() returns odd values for some reason, so use our own method instead
	protected boolean isFinished() {
		if (Math.abs(pid.getError()) < config.tolerance) {
			return true;
		}
		return false;
	}

	protected void end() {}

	protected void interrupted() {}

	@Override
	protected double returnPIDInput() {
		return this.encoder.getAverageVoltage();
	}

	@Override
	protected void usePIDOutput(double output) {
		this.motor.set(output);
	}
}
