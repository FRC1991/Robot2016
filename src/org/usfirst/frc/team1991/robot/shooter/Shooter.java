
package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.ShotPositions;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
    
	public boolean ballPresent = false;
	// Begin in stowed position
	ShotPositions currentPosition = ShotPositions.STOWED;

	CANTalon left = RobotMap.leftRunner;
	CANTalon right = RobotMap.rightRunner;
	CANTalon feeder = RobotMap.feeder;
	AnalogInput angleEncoder = RobotMap.angleEncoder;
	Talon angleMotor = RobotMap.angleMotor;
	DigitalInput intakeSensor = RobotMap.intakeSensor;

	public void run(double leftSpeed, double rightSpeed) {
		
		left.set(leftSpeed);
		right.set(rightSpeed);
	}
	
	public void feed(double speed) {
		feeder.set(speed);
	}
	
	public void stop() {
		feeder.set(0);
		left.set(0);
		right.set(0);
		// No ball present because true = no ball
		if (intakeSensor.get()) {
			ballPresent = false;
		}
	}
    public void initDefaultCommand() {
    	setTalonPID(left);
    	setTalonPID(right);
    }
    public void setTalonPID(CANTalon motor){
    	motor.changeControlMode(TalonControlMode.Speed);
    	motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	motor.setPID(1.0,0.0,0.0);
    }
}

