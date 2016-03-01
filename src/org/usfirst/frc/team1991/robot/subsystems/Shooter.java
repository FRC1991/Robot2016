package org.usfirst.frc.team1991.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.teleop.Drive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
public class Shooter extends PIDSubsystem {

  private CANTalon left, right, feeder, angle;
  private AnalogInput encoder;
  private double zeroPosition;
  private DigitalInput ballPresent;

  public Shooter() {
    super("Shooter", 1, 0, 0);
    setAbsoluteTolerance(0.2);
    getPIDController().setContinuous(true);
    setInputRange(0, 1.9);
    setOutputRange(-1, 1);
  }

  public void init() {
    left = new CANTalon(8);
    left.setInverted(true);
    right = new CANTalon(7);
    feeder = new CANTalon(10);
    encoder = new AnalogInput(0);
    angle = new CANTalon(9);
    angle.setInverted(true);
    ballPresent = new DigitalInput(0);
  }

  public void debug() {
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

  }

  public void run(double speed) {
    left.set(speed);
    right.set(speed);
  }

  public void feed(double speed) {
    feeder.set(speed);
  }

  public void disable() {
    super.disable();
    angle.set(0);
    left.set(0);
    right.set(0);
    feeder.set(0);
  }

  public boolean ballPresent() {
    // Signal is HIGH when no ball
    return !ballPresent.get();
  }

  protected double returnPIDInput() {
		return zeroPosition - encoder.getAverageVoltage();
	}

  protected void usePIDOutput(double output) {
    angle.set(output);
	}

  public void initDefaultCommand() {
	}

}
