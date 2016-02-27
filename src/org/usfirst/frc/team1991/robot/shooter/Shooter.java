
package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.Position;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	private CANTalon LRunner, RRunner, miniFeeder;
	private AnalogInput angleEncoder;
	public CANTalon angleMotor;
	private DigitalInput ballSensor;
	private Position currentPosition;

	public Shooter() {
		LRunner = RobotMap.shooter_LRunner;
		RRunner = RobotMap.shooter_RRunner;
		miniFeeder = RobotMap.shooter_miniFeeder;
		angleEncoder = RobotMap.shooter_angleEncoder;
		angleMotor = RobotMap.shooter_angleMotor;
		ballSensor = RobotMap.shooter_ballSensor;
		currentPosition = Position.SHOOTER_FAR_SHOT;
	}

	public void run(double leftSpeed, double rightSpeed) {
		LRunner.set(leftSpeed);
		RRunner.set(rightSpeed);
	}

	public void feed(double speed) {
		miniFeeder.set(speed);
	}

	public void move(double speed) {
		angleMotor.set(speed);
	}

	public void stop() {
		LRunner.set(0);
		RRunner.set(0);
		miniFeeder.set(0);
	}

  public boolean ballPresent() {
    // True = no ball
		boolean present = !ballSensor.get();
		SmartDashboard.putBoolean("Ball Present", present);
    return present;
  }

	public boolean isAtSpeed() {
		return true; // placeholder
	}

	public void setCurrentPosition(Position pos) {
		currentPosition = pos;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}
	public void initDefaultCommand() {
		// No default command
	}

	// public void setTalonPID(CANTalon motor){
	//      motor.changeControlMode(TalonControlMode.Speed);
	//      motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	//      motor.setPID(1.0,0.0,0.0);
}
