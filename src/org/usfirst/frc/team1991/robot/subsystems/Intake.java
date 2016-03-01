package org.usfirst.frc.team1991.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.teleop.Drive;

public class Intake extends PIDSubsystem {

  private CANTalon leftAngle, rightAngle;
  private Talon feeder;
  private AnalogInput encoder;
  private DigitalInput limitSwitch;
  private double zeroPosition;

  public Intake() {
    super("Intake", 1, 0, 0);
    setAbsoluteTolerance(0.2);
    getPIDController().setContinuous(false);
    setInputRange(0, 3.4);
    setOutputRange(-1, 1);
  }

  public void init() {
    leftAngle = new CANTalon(12);
    leftAngle.setInverted(true);
    rightAngle = new CANTalon(11);
    feeder = new Talon(0);
    encoder = new AnalogInput(1);
    limitSwitch = new DigitalInput(1);
  }

  public void debug() {
    LiveWindow.addActuator("Intake", "Left Angle", leftAngle);
    LiveWindow.addActuator("Intake", "Right Angle", rightAngle);
    LiveWindow.addActuator("Intake", "Feeder", feeder);
    LiveWindow.addSensor("Intake", "Encoder", encoder);
    LiveWindow.addActuator("Intake", "Angle PID", getPIDController());
  }

  public void periodic() {
    // Open = zero position
    if (limitSwitch.get() == false) {
      zeroPosition = encoder.getAverageVoltage();
    }
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

  protected double returnPIDInput() {
		return encoder.getAverageVoltage() - zeroPosition;
	}

  protected void usePIDOutput(double output) {
    leftAngle.set(output);
    rightAngle.set(output);
	}

  public void initDefaultCommand() {
	}

}
