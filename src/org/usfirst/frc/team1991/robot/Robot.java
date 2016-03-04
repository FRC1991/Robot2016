
package org.usfirst.frc.team1991.robot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import javax.swing.text.Position;
import org.usfirst.frc.team1991.robot.autonomous.Autonomous;
import org.usfirst.frc.team1991.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1991.robot.subsystems.Intake;
import org.usfirst.frc.team1991.robot.subsystems.Shooter;
import org.usfirst.frc.team1991.robot.teleop.DriveStraight;
import org.usfirst.frc.team1991.robot.teleop.Feed;
import org.usfirst.frc.team1991.robot.teleop.GoToSetpoint;
import org.usfirst.frc.team1991.robot.teleop.DualSetpoint;
import org.usfirst.frc.team1991.robot.teleop.ManualPosition;
import org.usfirst.frc.team1991.robot.teleop.Shoot;
import org.usfirst.frc.team1991.robot.teleop.XCommand;
import org.usfirst.frc.team1991.robot.teleop.XboxController;
// This code written by Andi Duro and Aakash Balaji
// Unless it doesn't work
// In which case, we don't know who wrote it
public class Robot extends IterativeRobot {

	public static Preferences prefs;
	public static XboxController driver;
	public static XboxController aux;
	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static Intake intake;
	public static Command autonomous;

	public enum Position {
		ShooterStowed(1.51), ShooterFeed(2.285), ShooterClose(0), ShooterFar(0), ShooterBarf(3.159),
		IntakeStowed(4.0), IntakeFeed(2.437);

		public final double setpoint;
		Position(double setpoint) {
			this.setpoint = setpoint;
		}
	}

	public void robotInit() {
		prefs = new Preferences("home/lvuser/DataFiles/prefs.txt");
		prefs.setValues();
		drivetrain = new Drivetrain();
		drivetrain.resetNavigation();
		shooter = new Shooter();
	  intake = new Intake();
		driver = new XboxController(0);
		autonomous = new Autonomous();
		aux = new XboxController(1);
		CameraServer.getInstance().setQuality(100);
		CameraServer.getInstance().startAutomaticCapture("cam1");
		registerControls();
	}

	// Driver controls
	public void registerControls() {
		driver.LBumper.whenPressed(new XCommand() {
			public void runOnce() {
			        drivetrain.toggleReverse();
			}
		});
		//driver.RBumper.whileHeld(new DriveStraight(false));
		//put shooter and intake in stow after shooting
		aux.LBumper.whileHeld(new Feed());
		aux.RBumper.whenPressed(new Shoot());
		aux.B.whenPressed(new DualSetpoint(Position.ShooterFeed, Position.IntakeFeed));
		aux.A.whenPressed(new DualSetpoint(Position.ShooterStowed, Position.IntakeFeed));
		aux.X.whenPressed(new GoToSetpoint(shooter, Position.ShooterStowed));
		aux.Y.whenPressed(new GoToSetpoint(shooter, Position.ShooterBarf));
		aux.LJoystick.whileHeld(new ManualPosition(shooter) {
			public double getSpeed() {
				return aux.getLJoystickY();
			}

			public void useOutput(double output) {
				shooter.move(output * 0.5);
			}
		});
		aux.RJoystick.whileHeld(new ManualPosition(intake) {
			public double getSpeed() {
				return aux.getRJoystickY();
			}

			public void useOutput(double output) {
				intake.move(output * 0.5);
			}
		});
	}

  // Code that runs periodicially regardless of mode
	public void genericPeriodic() {
		shooter.periodic();
		intake.periodic();
		Scheduler.getInstance().run();
	}

	public static double get(String key) {
		return prefs.get(key);
	}

	public void disabledInit() {
		drivetrain.disable();
		shooter.disable();
		intake.disable();
	}


	public void disabledPeriodic() {}


	public void autonomousInit() {
		prefs.setValues();
		autonomous.cancel();
		autonomous.start();
	}


	public void autonomousPeriodic() {
		genericPeriodic();
	}


	public void teleopInit() {
		Robot.drivetrain.disable();
		autonomous.cancel();
		prefs.setValues();
	}


	public void teleopPeriodic() {
		genericPeriodic();
	}

	public void testPeriodic() {
		genericPeriodic();
		LiveWindow.run();
	}
}
