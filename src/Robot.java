package src;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import src.autonomous.Autonomous;
import src.autonomous.MoveShooterToPosition;
import src.autonomous.MoveSystemsToPositions;
import src.autonomous.TurnToAlignWithTarget;
import src.autonomous.TurnToYaw;
import src.subsystems.CameraServer;
import src.subsystems.Climber;
import src.subsystems.Drivetrain;
import src.subsystems.Intake;
import src.subsystems.Shooter;
import src.teleop.Feed;
import src.teleop.MoveIntakeManually;
import src.teleop.MoveShooterManually;
import src.teleop.SetDriveBackwards;
import src.teleop.Shoot;
import src.teleop.StraightDrive;
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
	public static Climber climber;
	public static Command autonomous = null;

	public enum Position {
		ShooterStowed(2.08), ShooterFeed(2.95), ShooterAutoAim(3.14), ShooterBarf(3.95),
		IntakeStowed(4.1), IntakeFeed(2.358), IntakeDown(2.084);

		public final double setpoint;
		Position(double setpoint) {
			this.setpoint = setpoint;
		}
	}

	public void robotInit() {
		prefs = new Preferences("home/lvuser/DataFiles/prefs.txt");
		prefs.setValues();
		cameraServer = CameraServer.getInstance();
		drivetrain = new Drivetrain();
		drivetrain.resetNavigation();
		shooter = new Shooter();
		intake = new Intake();
		//climber = new Climber();
		driver = new XboxController(0);
		aux = new XboxController(1);
		try {
			cameraServer.setQuality(100);
			cameraServer.startAutomaticCapture("cam1");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		registerControls();
	}	
	
	public void auto(){
		autonomous = new Autonomous();
		drivetrain.setReverse(false);
		drivetrain.getNavX().zeroYaw();
		autonomous.start();
	}
	// Driver controls
	public void registerControls() {
		driver.LBumper.whenPressed(new SetDriveBackwards());
		driver.RBumper.whenPressed(new XCommand() {
			protected void execute() {
				try {
					String currentCam = cameraServer.getCameraName();
					cameraServer.startAutomaticCapture((currentCam == "cam2" ? "cam1" : "cam2"));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finish();
			}
		});
		driver.RJoystick.whileHeld(new StraightDrive());
		driver.X.whenPressed(new XCommand() {
			public void execute() {
				Robot.drivetrain.resetNavigation();
				finish();
			}
		});
		driver.A.whenPressed(new TurnToYaw(100));
		driver.B.whenPressed(new TurnToAlignWithTarget());
		aux.LBumper.whileHeld(new Feed());
		aux.RBumper.whenPressed(new Shoot());
		aux.B.whenPressed(new MoveSystemsToPositions(Position.IntakeFeed, Position.ShooterFeed));
		aux.A.whenPressed(new MoveSystemsToPositions(Position.IntakeStowed, Position.ShooterFeed));
		aux.X.whenPressed(new MoveShooterToPosition(Position.ShooterStowed));
		aux.Y.whenPressed(new MoveShooterToPosition(Position.ShooterBarf));
		aux.LJoystick.whileHeld(new MoveShooterManually());
		//aux.Select.whileHeld(new MoveClimberManually());
		aux.RJoystick.whileHeld(new MoveIntakeManually());
		// Climber code is run automatically in MoveClimberManually
	}

	
	// Code that runs periodically regardless of mode
	public void genericPeriodic() {
		drivetrain.periodic();
		shooter.periodic();
		intake.periodic();
		//climber.periodic();
		Scheduler.getInstance().run();
	}
	
	public void genericInit() {
		try {
			cameraServer.startAutomaticCapture(cameraServer.getCameraName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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
		//climber.disable();
	}


	public void disabledPeriodic() {}


	public void autonomousInit() {
		genericInit();
		prefs.setValues();
		auto();

	}


	public void autonomousPeriodic() {
		genericPeriodic();
	}


	public void teleopInit() {
		genericInit();
		if (autonomous != null) {
			autonomous.cancel();
		}
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
