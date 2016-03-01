package org.usfirst.frc.team1991.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.teleop.Drive;

public class Drivetrain extends PIDSubsystem {

  private CANTalon left, right;
  private AHRS navX;
  // Used for autonomous driving
  private double speed = 0;
  private boolean reverseMode = false;

  public Drivetrain() {
    super("Drivetrain", 0.045, 0.006, 0.115);
    setAbsoluteTolerance(0.5);
    getPIDController().setContinuous(true);
    setInputRange(-180.0, 180.0);
    setOutputRange(-1, 1);
  }

  public void init() {
    navX = new AHRS(SPI.Port.kMXP);
    // Ramp rate? See 6.3
    // Voltage Compensation Mode? See 9.2
    CANTalon R2, R3, L2, L3;
    right = new CANTalon(1);
    right.enableBrakeMode(true);
    right.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    R2 = new CANTalon(2);
    R2.enableBrakeMode(true);
    R2.changeControlMode(CANTalon.TalonControlMode.Follower);
    R2.set(1);
    R3 = new CANTalon(3);
    R3.enableBrakeMode(true);
    R3.changeControlMode(CANTalon.TalonControlMode.Follower);
    R3.set(1);
    left = new CANTalon(4);
    left.enableBrakeMode(true);
    left.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    L2 = new CANTalon(5);
    L2.enableBrakeMode(true);
    L2.changeControlMode(CANTalon.TalonControlMode.Follower);
    L2.set(4);
    L3 = new CANTalon(6);
    L3.enableBrakeMode(true);
    L3.changeControlMode(CANTalon.TalonControlMode.Follower);
    L3.set(4);
    // Go through each motor and enable brake mode and set up master-slave
    // for (CANTalon motor : new CANTalon[]{right, R2, R3, left, L2, L3}) {
    //   motor.enableBrakeMode(true);
    //   if (motor.getDeviceID() != right.getDeviceID() || motor.getDeviceID() != left.getDeviceID()) {
    //     motor.changeControlMode(CANTalon.TalonControlMode.Follower);
    //     int masterID = (motor.getDeviceID() <= 3) ? right.getDeviceID() : left.getDeviceID();
    //     motor.set(masterID);
    //   }
    // }
    right.setInverted(true);
  }

  public void debug() {
    LiveWindow.addSensor("Drivetrain", "navX", navX);
    LiveWindow.addActuator("Drivetrain", "Left Side", left);
    LiveWindow.addActuator("Drivetrain", "Right Side", right);
    LiveWindow.addActuator("Drivetrain", "Yaw PID", getPIDController());
  }

  public void toggleReverse() {
    reverseMode ^= true;
  }

  public void drive(double leftSpeed, double rightSpeed) {
    leftSpeed += Robot.get("Drivetrain_Offset_Left");
    rightSpeed += Robot.get("Drivetrain_Offset_Right");
    leftSpeed *= Robot.get("Drivetrain_Speed_Multiplier");
    rightSpeed *= Robot.get("Drivetrain_Speed_Multiplier");
    if (reverseMode) {
      left.set(-rightSpeed);
      right.set(-leftSpeed);
    }
    else {
      left.set(leftSpeed);
      right.set(rightSpeed);
    }
  }

  public void disable() {
    super.disable();
    left.set(0);
    right.set(0);
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

}
