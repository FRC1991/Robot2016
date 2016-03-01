
package org.usfirst.frc.team1991.robot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1991.robot.subsystems.*;
import org.usfirst.frc.team1991.robot.teleop.*;
import org.usfirst.frc.team1991.robot.teleop.XboxController;

// This code written by Andi Duro and Aakash Balaji
// Unless it doesn't work
// In which case, we don't know who wrote it
public class Robot extends IterativeRobot {

	private static Preferences prefs;
	public static XboxController driver;
	public static XboxController aux;
	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static Intake intake;

	public enum Position {
		ShooterStowed(0), ShooterFeed(0.848), ShooterClose(0), ShooterFar(0), ShooterBarf(1.768),
		IntakeStowed(0), IntakeFeed(2.45);

		public final double setpoint;
		Position(double setpoint) {
			this.setpoint = setpoint;
		}
	}

	public void robotInit() {
		prefs = new Preferences("home/lvuser/DataFiles/Preferences.txt");
		prefs.setValues();
		drivetrain = new Drivetrain();
		drivetrain.init();
		drivetrain.resetNavigation();
		shooter = new Shooter();
		shooter.init();
		intake = new Intake();
		intake.init();
		driver = new XboxController(0);
		aux = new XboxController(1);
		CameraServer.getInstance().setQuality(100);
		CameraServer.getInstance().startAutomaticCapture("cam1");
		registerControls();
		drivetrain.debug();
		shooter.debug();
		intake.debug();
	}

	// Driver controls
	public void registerControls() {
		driver.X.whenPressed(new MethodCommand() {
			public void call() {
			        drivetrain.toggleReverse();
			}
		});
		driver.RBumper.whileHeld(new DriveStraight());
		aux.A.whenPressed(new SetpointCommand(shooter, Position.ShooterStowed));
		aux.X.whenPressed(new SetpointCommand(shooter, Position.ShooterFeed));
		aux.Y.whenPressed(new SetpointCommand(shooter, Position.ShooterBarf));
		aux.LJoystick.whileHeld(new PositionSubsystem(shooter, 1));
		aux.RJoystick.whileHeld(new PositionSubsystem(intake, 5));

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
	}


	public void autonomousPeriodic() {
		genericPeriodic();
	}


	public void teleopInit() {
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
