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
	// Temp constants
	// Used for feeding ball in
	public static final double intakeFeedSpeed = 0.6;
	public static final double shooterFeedSpeed = 0.35;
	// Preferences
	public static Preferences prefs;
	// navX
	public static AHRS navX;
	// Drivetrain
	public static ArrayList<CANTalon> drivetrain_LSide, drivetrain_RSide;
	// Intake
	public static AnalogInput intake_angleEncoder;
	public static DigitalInput intake_limitSwitch;
	public static CANTalon intake_LAngleMotor, intake_RAngleMotor;
	public static Talon intake_feedMotor;
	// Shooter
	public static CANTalon shooter_LRunner, shooter_RRunner, shooter_miniFeeder, shooter_angleMotor;
	public static AnalogInput shooter_angleEncoder;
	public static DigitalInput shooter_ballSensor;
	// PID Settings
	public static PIDControllerConfiguration shooter_Up, shooter_Down, intake, drivetrain_TurnInPlace;

	public static void reloadPreferences() {
		prefs.setValues();
		// PID Settings
		shooter_Up =  new PIDControllerConfiguration(1, 0.05, 0.05, 0.01, 0, 1.9, false);
		shooter_Down =  new PIDControllerConfiguration(0.1, 0.05, 0.05, 0.002, 0, 1.9, false);
		intake = new PIDControllerConfiguration(0.2, 0, 0, 0.008, 0, 3.4, false);
		drivetrain_TurnInPlace = new PIDControllerConfiguration("DriveTrain_Turn");
	}

	public static void init() {
		// Preferences
		prefs = new Preferences("home/lvuser/DataFiles/Preferences.txt");
		reloadPreferences();
		// navX
		navX = new AHRS(SPI.Port.kMXP);
		// Drivetrain
		drivetrain_LSide = new ArrayList<CANTalon>();
		drivetrain_RSide = new ArrayList<CANTalon>();
		drivetrain_RSide.add(new CANTalon(1));
		drivetrain_RSide.add(new CANTalon(2));
		drivetrain_RSide.add(new CANTalon(3));
		for (CANTalon motor : drivetrain_RSide) {
			motor.setInverted(true);
		}
		drivetrain_LSide.add(new CANTalon(4));
		drivetrain_LSide.add(new CANTalon(5));
		drivetrain_LSide.add(new CANTalon(6));
		// Intake
		intake_angleEncoder = new AnalogInput(1);
		intake_LAngleMotor = new CANTalon(12);
		intake_LAngleMotor.setInverted(true);
		intake_RAngleMotor = new CANTalon(11);
		intake_feedMotor = new Talon(0);
		intake_limitSwitch = new DigitalInput(1);
		// Shooter
		shooter_LRunner = new CANTalon(8);
		shooter_RRunner = new CANTalon(7);
		shooter_LRunner.setInverted(true);
		shooter_miniFeeder = new CANTalon(10);
	  shooter_angleEncoder = new AnalogInput(0);
		shooter_angleMotor = new CANTalon(9);
		shooter_ballSensor = new DigitalInput(0);
		LiveWindow.addActuator("Shooter", "Left", shooter_LRunner);
		LiveWindow.addActuator("Shooter", "Right", shooter_RRunner);
	}
}
