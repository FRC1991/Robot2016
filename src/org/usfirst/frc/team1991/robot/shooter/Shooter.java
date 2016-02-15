
package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
    
	CANTalon left = RobotMap.leftRunner;
	CANTalon right = RobotMap.rightRunner;
	CANTalon feeder = RobotMap.feeder;
	AnalogInput angleEncoder = RobotMap.angleEncoder;
	Talon angleMotor = RobotMap.angleMotor;

	public void run(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed * -1);
	}
	
	public void feed() {
		feeder.set(-0.7);
	}
	
	public void stop() {
		feeder.set(0);
		left.set(0);
		right.set(0);
	}
    public void initDefaultCommand() {
    }
}

