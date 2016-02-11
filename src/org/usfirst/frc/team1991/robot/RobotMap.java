package org.usfirst.frc.team1991.robot;

import java.util.List;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static List<CANTalon> leftDriveSide = new ArrayList<CANTalon>();
	public static List<CANTalon> rightDriveSide = new ArrayList<CANTalon>();
	public static CANTalon leftShooter;
	public static CANTalon rightShooter;
	public static CANTalon feedShooter;
	
	
	public static void init() {
//		leftDriveSide.add(new CANTalon(1));
//		leftDriveSide.add(new CANTalon(2));
//		leftDriveSide.add(new CANTalon(3));
//		rightDriveSide.add(new CANTalon(4));
//		rightDriveSide.add(new CANTalon(5));
//		rightDriveSide.add(new CANTalon(6));
		leftShooter = new CANTalon(9);
		rightShooter = new CANTalon(10);
		feedShooter = new CANTalon(11);
	}
}
