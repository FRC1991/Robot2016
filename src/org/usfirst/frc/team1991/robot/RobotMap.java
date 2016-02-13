package org.usfirst.frc.team1991.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Drivetrain
	public static List<CANTalon> leftSide = new ArrayList<CANTalon>();
	public static List<CANTalon> rightSide = new ArrayList<CANTalon>();
	// Shooter
	public static CANTalon leftRunner;
	public static CANTalon rightRunner;
	public static CANTalon feeder;
	public static AnalogInput angleEncoder;
	public static Talon angleMotor;
	
	
	
	public static void init() {
//		leftSide.add(new CANTalon(1));
//		leftSide.add(new CANTalon(2));
//		leftSide.add(new CANTalon(3));
//		rightSide.add(new CANTalon(4));
//		rightSide.add(new CANTalon(5));
//		rightSide.add(new CANTalon(6));
//		leftRunner = new CANTalon(9);
//		rightRunner = new CANTalon(10);
//		feeder = new CANTalon(11);
		angleEncoder = new AnalogInput(4);
		angleMotor = new Talon(10);
		LiveWindow.addSensor("Test", "Enc", angleEncoder);
		LiveWindow.addActuator("Test", "Motor", angleMotor);
	}
}
