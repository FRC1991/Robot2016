
package org.usfirst.frc.team1991.robot.drivetrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1991.robot.drivetrain.Drive.ManualDriveModes;

/**
 * The subsystem responsible for controlling the motors that run the drivetrain.
 */
public class Drivetrain extends Subsystem {

	ArrayList<CANTalon> leftSide = RobotMap.drivetrain_LSide;
	ArrayList<CANTalon> rightSide = RobotMap.drivetrain_RSide;

	private boolean reverse = false;

	public boolean isReverse() {
		return reverse;
	}

	public void toggleReverse() {
		if (reverse) {
			reverse = false;
		}
		else {
			reverse = true;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualDrive());
	}

	private void driveSide(ArrayList<CANTalon> side, double speed) {
		for (CANTalon motor : side) {
			motor.set(speed);
		}
	}

	public void drive(double leftSpeed, double rightSpeed) {
		// Perform any final operations on the two speeds
		leftSpeed += RobotMap.prefs.get("Drivetrain_Offset_Left");
		rightSpeed += RobotMap.prefs.get("Drivetrain_Offset_Right");
		leftSpeed *= RobotMap.prefs.get("Drivetrain_Speed_Multiplier");
		rightSpeed *= RobotMap.prefs.get("Drivetrain_Speed_Multiplier");
		if (reverse) {
			driveSide(leftSide, -rightSpeed);
			driveSide(rightSide, -leftSpeed);
		}
		else {
			driveSide(leftSide, leftSpeed);
			driveSide(rightSide, rightSpeed);
		}
	}

	public void stop() {
		driveSide(leftSide, 0);
		driveSide(rightSide, 0);
	}
}
