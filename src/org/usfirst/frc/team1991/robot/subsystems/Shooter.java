
package org.usfirst.frc.team1991.robot.subsystems;

import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
    
	CANTalon left = RobotMap.leftShooter;
	CANTalon right = RobotMap.rightShooter;
	CANTalon feeder = RobotMap.feedShooter;

	public void run(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
	}
	
	public void feed() {
		feeder.set(-0.3);
	}
	
    public void initDefaultCommand() {
    }
}

