package org.usfirst.frc.team1991.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends PIDSubsystem {

  private CANTalon leftAngle, rightAngle;
  private Talon feeder;
  private AnalogInput encoder;
  private DigitalInput limitSwitch;
  private double zeroPosition;

  public Intake() {
    super("Intake", 0.5, 0, 0);
    setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(false);
    setInputRange(0.7, 4.3);
    setOutputRange(-1, 1);
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
    LiveWindow.addActuator("Intake", "Angle PID", getPIDController());
  }

  public void periodic() {
    // // Open = zero position
    // if (limitSwitch.get() == false) {
    //   zeroPosition = encoder.getAverageVoltage();
    // }
    SmartDashboard.putNumber("Intake Encoder", encoder.getAverageVoltage());
    SmartDashboard.putBoolean("Intake Enabled", getPIDController().isEnabled());
    SmartDashboard.putNumber("Intake Error", getPIDController().getError());
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
	  usePIDOutput(speed * -1);
  }

  protected double returnPIDInput() {
		return encoder.getAverageVoltage(); // - zeroPosition;
	}

  protected void usePIDOutput(double output) {
    leftAngle.set(output);
    rightAngle.set(output);
	}

  public void initDefaultCommand() {
	}

}
