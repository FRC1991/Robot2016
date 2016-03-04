package org.usfirst.frc.team1991.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends SwegSystem {

  private CANTalon leftAngle, rightAngle;
  private Talon feeder;
  private AnalogInput encoder;
  private DigitalInput limitSwitch;

  public Intake() {
    super(0.6, -0.4, 0.6, 4.1, 0.1);
    leftAngle = new CANTalon(12);
    leftAngle.setInverted(true);
    rightAngle = new CANTalon(11);
    feeder = new Talon(0);
    encoder = new AnalogInput(1);
    limitSwitch = new DigitalInput(1);
    LiveWindow.addActuator("Intake", "Left Angle", leftAngle);
    LiveWindow.addActuator("Intake", "Right Angle", rightAngle);
    LiveWindow.addActuator("Intake", "Feeder", feeder);
    LiveWindow.addSensor("Intake", "Encoder", encoder);
  }

  public void periodic() {
    super.periodic();
    SmartDashboard.putNumber("Intake Setpoint", getSetpoint());
    SmartDashboard.putNumber("Intake Position", getCurrentPosition());
    SmartDashboard.putBoolean("Intake Enabled", isEnabled());
    SmartDashboard.putBoolean("Intake On Target", onPoint());
    SmartDashboard.putNumber("Intake Error", getError());
  }

  public void feed(double speed) {
    feeder.set(speed);
  }

  public void disable() {
    super.disable();
    leftAngle.set(0);
    rightAngle.set(0);
    feeder.set(0);
  }

  public void move(double speed) {
    // Invert so moving joystick up moves intake up
	  leftAngle.set(speed *-1);
    rightAngle.set(speed *-1);
    System.out.println(speed);
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
