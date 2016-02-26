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
	public static final double intakeFeedSpeed = 0.5;
	public static final double shooterFeedSpeed = 0.35;
	// Preferences
	public static Preferences prefs;
	// navX
	public static AHRS navX;
	// Drivetrain
	public static ArrayList<CANTalon> drivetrain_LSide, drivetrain_RSide;
	// Intake
	public static AnalogInput intake_angleEncoder;
	public static CANTalon intake_LAngleMotor, intake_RAngleMotor, intake_feedMotor;
	// Shooter
	public static CANTalon shooter_LRunner, shooter_RRunner, shooter_miniFeeder;
	public static AnalogInput shooter_angleEncoder;
	public static Talon shooter_angleMotor;
	public static DigitalInput shooter_ballSensor;
	// PID Settings
	public static PIDControllerConfiguration shooter_Up, shooter_Down, intake_Up, intake_Down, drivetrain_TurnInPlace;

	public static void init() {
		// Preferences
		prefs = new Preferences("home/lvuser/DataFiles/Preferences.txt");
		prefs.setValues();
		// PID Settings
		shooter_Up = new PIDControllerConfiguration("Shooter_Up");
		shooter_Down = new PIDControllerConfiguration("Shooter_Down");
		intake_Up = new PIDControllerConfiguration("Intake_Up");
		intake_Down = new PIDControllerConfiguration("Intake_Down");
		drivetrain_TurnInPlace = new PIDControllerConfiguration("DriveTrain_Turn");
		// navX
		navX = new AHRS(SPI.Port.kMXP);
		// Drivetrain
		drivetrain_LSide = new ArrayList<CANTalon>();
		drivetrain_RSide = new ArrayList<CANTalon>();
		drivetrain_LSide.add(new CANTalon(1));
		drivetrain_LSide.add(new CANTalon(2));
		drivetrain_LSide.add(new CANTalon(3));
		drivetrain_RSide.add(new CANTalon(4));
		drivetrain_RSide.add(new CANTalon(5));
		drivetrain_RSide.add(new CANTalon(6));
		// Intake
		intake_angleEncoder = new AnalogInput(1);
		intake_LAngleMotor = new CANTalon(5);
		intake_RAngleMotor = new CANTalon(5);
		intake_feedMotor = new CANTalon(5);
		// Shooter
		shooter_LRunner = new CANTalon(5);
		shooter_RRunner = new CANTalon(5);
		shooter_RRunner.setInverted(true);
		shooter_miniFeeder = new CANTalon(5);
	  shooter_angleEncoder = new AnalogInput(0);
		shooter_angleMotor = new Talon(0);
		shooter_ballSensor = new DigitalInput(0);
	}
}
