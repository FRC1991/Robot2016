
package org.usfirst.frc.team1991.robot.intake;

import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.ShotPositions;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
    
	public boolean ballPresent = false;
	// Begin in stowed position
	ShotPositions currentPosition = ShotPositions.DOWN;

	CANTalon anglerR = RobotMap.intakeMotorR;
	CANTalon anglerL = RobotMap.intakeMotorL;
	CANTalon feeder = RobotMap.intakeFeedMotor;
	AnalogInput intakeAngleEncoder = RobotMap.intakeEncoder;
	DigitalInput intakeSensor = RobotMap.intakeSensor;

	public void feed(double speed) {
		feeder.set(speed);
		
	}
	
	public void stop() {
		feeder.set(0.0);
		anglerR.set(0);
		anglerL.set(0);
		
	}
	public void move(double speed){
		anglerR.set(speed);
		anglerL.set(speed);
	}
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeTest());
    }
}

