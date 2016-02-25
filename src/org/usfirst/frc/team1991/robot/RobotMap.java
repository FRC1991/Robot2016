package org.usfirst.frc.team1991.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class RobotMap {
	// navX
	public static AHRS navX;
	// Drivetrain
	public static CANTalon LSide;
	public static CANTalon RSide;
	// Intake
	public static AnalogInput intakeEncoder;
	public static CANTalon intakeMotor;
	public static CANTalon leftIntakeAngleMotor;
	public static CANTalon intakeMotorL;
	// Feeder
	public static CANTalon leftRunner;
	public static CANTalon rightRunner;
	public static CANTalon feeder;
	public static AnalogInput angleEncoder;
	public static Talon angleMotor;
	public static DigitalInput intakeSensor;

	//Preferences
	public static Preferences pref;


	public static void init() {
		//Preferences
		pref = new Preferences("home/lvuser/DataFiles/Preferences.txt");
		pref.setValues();

		// Drivetrain
		rightSide = new Talon(2);
		leftSide = new Talon(1);
		leftSide.setInverted(true);

		// navX
		navX = new AHRS(SPI.Port.kMXP);
		// Feeder
		leftRunner = new CANTalon(9);
		rightRunner = new CANTalon(10);
		rightRunner.setInverted(true);
		feeder = new CANTalon(8);
		angleEncoder = new AnalogInput(0);
		angleMotor = new Talon(0);
		intakeSensor = new DigitalInput(0);

		//Intake
		intakeEncoder = new AnalogInput(1);
		intakeMotorL = new CANTalon(5);
		intakeMotorR = new CANTalon(4);
		intakeFeedMotor = new CANTalon(6);

		// Debugging
		LiveWindow.addActuator("Feeder", "Left", leftRunner);
		LiveWindow.addActuator("Feeder", "Right", rightRunner);
		LiveWindow.addActuator("Feeder", "Feed", feeder);
		LiveWindow.addSensor("Test", "Enc", angleEncoder);
		LiveWindow.addActuator("Test", "Motor", angleMotor);

		LiveWindow.addActuator("Intake", "Feed", intakeFeedMotor);
		LiveWindow.addActuator("Intake", "AngleL", intakeMotorL);
		LiveWindow.addSensor("Intake", "Enc", intakeEncoder);
		LiveWindow.addSensor("Intake", "AngleR", intakeMotorR);
	}
}
