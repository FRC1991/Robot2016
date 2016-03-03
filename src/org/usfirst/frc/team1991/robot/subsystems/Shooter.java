package org.usfirst.frc.team1991.robot.subsystems;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends PIDSubsystem {

  private CANTalon left, right, feeder, angle;
  private AnalogInput encoder;
  private double zeroPosition;
  private DigitalInput ballPresent;

  public Shooter() {
    super("Shooter", 3, 0, 0);
    setAbsoluteTolerance(0.1);
    getPIDController().setContinuous(false);
    setInputRange(1.726, 3.486);
    setOutputRange(-1, 1);
    left = new CANTalon(8);
    left.setInverted(true);
    right = new CANTalon(7);
    feeder = new CANTalon(10);
    encoder = new AnalogInput(0);
    angle = new CANTalon(9);
    angle.setInverted(true);
    ballPresent = new DigitalInput(0);
    LiveWindow.addActuator("Shooter", "Left Runner", left);
    LiveWindow.addActuator("Shooter", "Right Runner", right);
    LiveWindow.addActuator("Shooter", "Feeder", feeder);
    LiveWindow.addSensor("Shooter", "Ball Present", ballPresent);
    LiveWindow.addActuator("Shooter", "Angle", angle);
    LiveWindow.addSensor("Shooter", "Encoder", encoder);
    LiveWindow.addActuator("Shooter", "Angle PID", getPIDController());
  }

  public void periodic() {
    // Open = zero position
    if (angle.isFwdLimitSwitchClosed() == false) {
      zeroPosition = encoder.getAverageVoltage();
    }
    if (onTarget()) {
      disable();
      System.out.println("Disabled because shooter on target.");
    }
    SmartDashboard.putNumber("Shooter Encoder", encoder.getAverageVoltage());
    SmartDashboard.putNumber("Inverted Shooter Encoder", 5 - encoder.getAverageVoltage());
    SmartDashboard.putNumber("Shooter Encoder", encoder.getAverageVoltage());
    SmartDashboard.putBoolean("Shooter Enabled", getPIDController().isEnabled());
    SmartDashboard.putBoolean("Shooter On Target", getPIDController().onTarget());
    SmartDashboard.putNumber("Shooter Error", getPIDController().getError());
  }

  public void run(double speed) {
    left.set(speed);
    right.set(speed);
  }

  public void feed(double speed) {
    feeder.set(speed);
  }

  public void move(double speed) {
	  angle.set(speed);
  }

  public void disable() {
    super.disable();
    angle.set(0);
    left.set(0);
    right.set(0);
    feeder.set(0);
  }

  public boolean ballPresent() {
    return ballPresent.get();
  }

  public double getEncoderValue() {
      return 5 - encoder.getAverageVoltage();
  }

  protected double returnPIDInput() {
		return 5 - encoder.getAverageVoltage();
	}

  protected void usePIDOutput(double output) {
    move(output);
	}

  public void initDefaultCommand() {
	}

}
