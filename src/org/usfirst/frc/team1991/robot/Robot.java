
package org.usfirst.frc.team1991.robot;

import org.usfirst.frc.team1991.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team1991.robot.drivetrain.StraightDrive;
import org.usfirst.frc.team1991.robot.drivetrain.TurnToAngle;
import org.usfirst.frc.team1991.robot.intake.Intake;
import org.usfirst.frc.team1991.robot.shooter.MoveShooter;
import org.usfirst.frc.team1991.robot.shooter.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static OI oi;
	public static Intake intake;
	public static Camera cam;
	public static Command autonomous;

	@Override
	public void robotInit() {
		initialMessages();
		RobotMap.init();
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		oi = new OI();
		intake = new Intake();
		cam = new Camera();
		autonomous = new StraightDrive(0.9, 8);
		// SmartDashboard.putNumber("Angle_Turn_P", 0.04);
		// SmartDashboard.putNumber("Angle_Turn_I", 0.006);
		// SmartDashboard.putNumber("Angle_Turn_D", 0.115);
	}

	public void initialMessages() {
		System.out.println("No drive motors have been inverted. This may be a problem. RobotMap");
		System.out.println("Wire mappings must be reconfigured. RobotMap");
		System.out.println("Shooter timeout is set to 5 seconds. Perhaps read from file instead. FireShooter");
		System.out.println("isAtSpeed always returns true. FireShooter");
	}

	@Override
	public void disabledInit() {
		Robot.shooter.stop();
		Robot.drivetrain.stop();
		Robot.intake.stop();
		Robot.cam.stop();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		RobotMap.prefs.readPrefs();
		RobotMap.navX.reset();
		cam.start("cam1");
		autonomous.start();
	}

	@Override
	public void autonomousPeriodic() {
		cam.refreshFrame();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		RobotMap.prefs.readPrefs();
		if (autonomous != null) autonomous.cancel();
		RobotMap.navX.reset();
		cam.start("cam1");
	}

	@Override
	public void teleopPeriodic() {
		cam.refreshFrame();
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
