package src;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.vision.USBCamera;
import src.autonomous.Autonomous;
import src.subsystems.Drivetrain;
import src.subsystems.CameraServer;
import src.subsystems.Intake;
import src.subsystems.Shooter;
import src.teleop.DualSetpoint;
import src.teleop.Feed;
import src.teleop.GoToSetpoint;
import src.teleop.ManualPosition;
import src.teleop.Shoot;
import src.teleop.XboxController;
// This code written by Andi Duro and Aakash Balaji
// Unless it doesn't work
// In which case, we don't know who wrote it
public class Robot extends IterativeRobot {

	public static Preferences prefs;
	public static XboxController driver;
	public static XboxController aux;
	public static CameraServer cameraServer;
	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static Intake intake;
	public static Command autonomous;

	public enum Position {
		ShooterStowed(2.08), ShooterFeed(3.057), ShooterBarf(3.95),
		IntakeStowed(4.1), IntakeFeed(2.358), IntakeDown(2.084);

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
		aux = new XboxController(1);
		cameraServer = CameraServer.getInstance();
		try {
			cameraServer.setQuality(100);
			cameraServer.startAutomaticCapture("cam0");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		registerControls();
	}
	public void auto(){
		autonomous = new Autonomous(1);
		drivetrain.setReverse(false);
		autonomous.cancel();
		autonomous.start();
		System.out.println("Autonomous period over");
	}
	// Driver controls
	public void registerControls() {
		driver.LBumper.whenPressed(new XCommand() {
			public void runOnce() {
			        drivetrain.toggleReverse();
			}
		});

		//driver.RBumper.whileHeld(new DriveStraight(-1,false, false));
		//driver.Y.whileHeld(new DriveStraight(true, 90));
		driver.RBumper.whenPressed(new XCommand() {
			protected void runOnce() {
				try {
					String currentCam = cameraServer.getCamera();
					System.out.println(currentCam);
					cameraServer.startAutomaticCapture((currentCam == "cam0" ? "cam2" : "cam0"));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
			        shooter.move(output * 0.6);
			}
		});
		aux.RJoystick.whileHeld(new ManualPosition(intake) {
			public double getSpeed() {
			        return aux.getRJoystickY();
			}

			public void useOutput(double output) {
			        intake.move(output * 0.8);
			}
		});
	}

	// Code that runs periodicially regardless of mode
	public void genericPeriodic() {
		drivetrain.periodic();
		shooter.periodic();
		intake.periodic();

		Scheduler.getInstance().run();
	}

	public static double get(String key) {
		return prefs.get(key);
	}

	public static void print(String output) {
		System.out.println(output);
	}

	public void disabledInit() {
		drivetrain.disable();
		shooter.disable();
		intake.disable();
	}


	public void disabledPeriodic() {}


	public void autonomousInit() {
		prefs.setValues();
		auto();

	}


	public void autonomousPeriodic() {
		genericPeriodic();
	}


	public void teleopInit() {
		Robot.drivetrain.disable();
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
