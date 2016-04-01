package src.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends SwegSystem {

  private CANTalon angle, leftFeeder, rightFeeder;
  private AnalogInput encoder;
  private DigitalInput limitSwitch;

  public Intake() {
    super(0.6, -0.4, 0, 5, 0.1);
    angle = new CANTalon(11);
    leftFeeder = new CANTalon(12);
    leftFeeder.setInverted(true);
    rightFeeder = new CANTalon(13);
    encoder = new AnalogInput(1);
    limitSwitch = new DigitalInput(1);
    LiveWindow.addActuator("Intake", "Angle", angle);
    LiveWindow.addActuator("Intake", "Right Feeder", rightFeeder);
    LiveWindow.addActuator("Intake", "Left Feeder", leftFeeder);
    LiveWindow.addSensor("Intake", "Encoder", encoder);
  }

  public void periodic() {
    super.periodic();
    SmartDashboard.putNumber("Intake Angle Setpoint", getSetpoint());
    SmartDashboard.putNumber("Intake Angle", getCurrentPosition());
    SmartDashboard.putBoolean("Intake Enabled", isEnabled());
    SmartDashboard.putBoolean("Intake On Target", onPoint());
    SmartDashboard.putNumber("Intake Error", getError());
  }

  public void feed(double speed) {
    leftFeeder.set(speed);
    rightFeeder.set(speed);
  }

  public void disable() {
    super.disable();
    leftFeeder.set(0);
    rightFeeder.set(0);
    angle.set(0);
  }

  public void move(double speed) {
	  angle.set(speed);
  }

  public void useOutput(double output) {
    move(output);
  }

  public double getCurrentPosition() {
    return 5 - encoder.getAverageVoltage();
  }

  public void initDefaultCommand() {
	}

}
