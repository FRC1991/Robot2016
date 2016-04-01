package src.subsystems;
import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.teleop.Drive;

public class Drivetrain extends PIDSubsystem {

	private ArrayList<CANTalon> right, left;
	private boolean testMode = false;
	private AHRS navX;
	private double speed = 0;
	private double tolerance = 0.7;
	private boolean reverseMode = false;

	public Drivetrain() {
		super("Drivetrain", 0.05, 0.005, 0.115);
		getPIDController().setContinuous(true);
		setAbsoluteTolerance(tolerance);
		setInputRange(-180.0, 180.0);
		setOutputRange(-0.4, 0.4);
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
		SmartDashboard.putNumber("Test P", getPIDController().getP());
		SmartDashboard.putNumber("Test I", getPIDController().getI());
		SmartDashboard.putNumber("Test D", getPIDController().getD());
		SmartDashboard.putBoolean("Test PID Mode", testMode);
		LiveWindow.addActuator("Drivetrain", "navX", navX);
		LiveWindow.addActuator("Drivetrain", "Yaw PID", getPIDController());
	}

	public void initSide(ArrayList<CANTalon> side, boolean inverted) {
		for (CANTalon motor: side) {
			motor.enableBrakeMode(true);
			motor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			motor.setInverted(inverted);
		}
	}

	public void periodic() {
		if (testMode) {
			getPIDController().setPID(SmartDashboard.getNumber("Test P"), SmartDashboard.getNumber("Test I"),SmartDashboard.getNumber("Test D"));
		}
		SmartDashboard.putNumber("Yaw", navX.getYaw());
		SmartDashboard.putBoolean("Yaw On Target", onTarget());
		SmartDashboard.putNumber("Yaw Setpoint", getSetpoint());
		SmartDashboard.putNumber("Yaw Error", getPIDController().getError());
		SmartDashboard.putNumber("Displacement X", navX.getDisplacementX());
		SmartDashboard.putNumber("Displacement Y", navX.getDisplacementY());
		SmartDashboard.putNumber("Displacement Z", navX.getDisplacementZ());
		SmartDashboard.putNumber("Pitch", navX.getPitch());
		double totalDisplacement = Math.sqrt((navX.getDisplacementX()*navX.getDisplacementX()) + (navX.getDisplacementY()*navX.getDisplacementY()));
		SmartDashboard.putNumber("Displacement Total", totalDisplacement);
	}

	public boolean isReversed() {
		return reverseMode;
	}
	
	public void setReverse(boolean reverse) {
		this.reverseMode = reverse;
	}

	public void driveSide(ArrayList<CANTalon> side, double speed) {
		for(CANTalon motor: side) {
			motor.set(speed);
		}
	}

	public void drive(double leftSpeed, double rightSpeed) {
			if (reverseMode) {
				driveSide(left, -rightSpeed);
				driveSide(right, -leftSpeed);
			}
			else {
				driveSide(left, leftSpeed);
				driveSide(right, rightSpeed);
			}		
	}
	
	public boolean onTarget() {
		return (getError() < tolerance);
	}
	
	public double getError() {
		return Math.abs(getSetpoint() - getPosition());
	}

	public void disable() {
		super.disable();
		driveSide(left, 0);
		driveSide(right, 0);
	}

	public void resetNavigation() {
		navX.reset();
		navX.resetDisplacement();
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
	
	public AHRS getNavX(){
		return navX;
	}

}
