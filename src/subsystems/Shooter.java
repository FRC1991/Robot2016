package src.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends SwegSystem {

  private CANTalon left, right, feeder, angle;
  private AnalogInput encoder;
  private DigitalInput ballPresent;

  public Shooter() {
    super(0.8, -0.3, 0, 5, 0.06);
    left = new CANTalon(8);
    left.setInverted(true);
    right = new CANTalon(7);
    feeder = new CANTalon(10);
    encoder = new AnalogInput(2); 
    angle = new CANTalon(9);
    angle.setInverted(true);
    ballPresent = new DigitalInput(0);
    LiveWindow.addActuator("Shooter", "Left Runner", left);
    LiveWindow.addActuator("Shooter", "Right Runner", right);
    LiveWindow.addActuator("Shooter", "Feeder", feeder);
    LiveWindow.addSensor("Shooter", "Ball Present", ballPresent);
    LiveWindow.addActuator("Shooter", "Angle", angle);
    LiveWindow.addSensor("Shooter", "Encoder", encoder);
  }

  public void periodic() {
    super.periodic();
    SmartDashboard.putNumber("Shooter Angle Setpoint", getSetpoint());
    SmartDashboard.putNumber("Shooter Angle", getCurrentPosition());
    SmartDashboard.putBoolean("Shooter Enabled", isEnabled());
    SmartDashboard.putBoolean("Shooter Angle On Target", onPoint());
    SmartDashboard.putNumber("Shooter Error", getError());
    SmartDashboard.putBoolean("Ball Present", ballPresent());
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

  public void useOutput(double output) {
    move(output);
  }

  public double getCurrentPosition() {
    return 5 - encoder.getAverageVoltage();
  }

  public void initDefaultCommand() {}

}
