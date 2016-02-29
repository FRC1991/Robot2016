
package org.usfirst.frc.team1991.robot.intake;

import org.usfirst.frc.team1991.robot.Position;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	CANTalon LAngle = RobotMap.intake_LAngleMotor;
	CANTalon RAngle = RobotMap.intake_RAngleMotor;
	Talon feeder = RobotMap.intake_feedMotor;
	AnalogInput encoder = RobotMap.intake_angleEncoder;
	public double encoderZero = 0.8;
	DigitalInput zeroSwitch = RobotMap.intake_limitSwitch;

	public void feed(double speed) {
		feeder.set(speed);
	}

	public void stop() {
		feeder.set(0);
		LAngle.set(0);
		RAngle.set(0);
	}

	public void move(double speed) {
		LAngle.set(speed);
		RAngle.set(speed);
	}

	public void initDefaultCommand() {
		// No default command
	}
}
