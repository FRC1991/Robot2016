package org.usfirst.frc.team1991.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.ArrayList;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.teleop.Drive;

public class Drivetrain extends PIDSubsystem {

	private ArrayList<CANTalon> right, left;
	private AHRS navX;
	// Used for autonomous driving
	private double speed = 0;
	private boolean reverseMode = false;
	private double continuous;

	public Drivetrain() {
		super("Drivetrain", Robot.prefs.get("DriveTrain_Turn_P"), Robot.prefs.get("DriveTrain_Turn_I"),
		      Robot.prefs.get("DriveTrain_Turn_D"));
		//super("Drivetrain", .045, .006, .115);
		continuous = Robot.prefs.get("DriveTrain_Turn_Continuous");
		setAbsoluteTolerance(Robot.prefs.get("DriveTrain_Turn_Tolerance"));
		if(continuous == 0.0) {
			getPIDController().setContinuous(false);
		}
		else{
			getPIDController().setContinuous(true);
		}
		setInputRange(Robot.prefs.get("DriveTrain_Turn_Min"), Robot.prefs.get("DriveTrain_Turn_Max"));
		setOutputRange(-1, 1);
		navX = new AHRS(SPI.Port.kMXP);
		left = new ArrayList<CANTalon>();
		right = new ArrayList<CANTalon>();
		// Ramp rate? See 6.3
		// Voltage Compensation Mode? See 9.2
		right.add(new CANTalon(1));
		right.add(new CANTalon(2));
		right.add(new CANTalon(3));
		left.add(new CANTalon(4));
		left.add(new CANTalon(5));
		left.add(new CANTalon(6));
		initSide(left, false);
		initSide(right, true);
		LiveWindow.addActuator("Drivetrain", "navX", navX);
		LiveWindow.addActuator("Drivetrain", "Yaw PID", getPIDController());
	}

	public void toggleReverse() {
		reverseMode ^= true;
	}
	public void initSide(ArrayList<CANTalon> side, boolean inverted) {
		for(CANTalon motor: side) {
			motor.enableBrakeMode(true);
			motor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			motor.setInverted(inverted);
		}
	}
	public void driveSide(ArrayList<CANTalon> side, double speed){
		for(CANTalon motor: side) {
			motor.set(speed);
		}
	}

	public void drive(double leftSpeed, double rightSpeed) {
		leftSpeed += Robot.get("DriveTrain_Offset_Left");
		rightSpeed += Robot.get("DriveTrain_Offset_Right");
		leftSpeed *= Robot.get("DriveTrain_Speed_Multiplier");
		rightSpeed *= Robot.get("DriveTrain_Speed_Multiplier");
		if (reverseMode) {
			driveSide(left, -rightSpeed);
			driveSide(right, -leftSpeed);
		}
		else {
			driveSide(left, leftSpeed);
			driveSide(right, rightSpeed);
		}
	}

	public void disable() {
		super.disable();
		driveSide(left, 0);
		driveSide(right, 0);
	}

	public void resetNavigation() {
		navX.reset();
	}

	public void setYawAndSpeed(double yaw, double speed) {
		this.speed = speed;
		setSetpoint(yaw);
	}

	protected double returnPIDInput() {
		return navX.getYaw();
	}

	protected void usePIDOutput(double output) {
		drive(speed + output, speed - output);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public double getPitch() {
		return navX.getPitch();
	}

}
