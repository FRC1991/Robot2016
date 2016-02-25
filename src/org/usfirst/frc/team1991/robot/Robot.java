
package org.usfirst.frc.team1991.robot;

import org.usfirst.frc.team1991.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team1991.robot.drivetrain.StraightDrive;
import org.usfirst.frc.team1991.robot.drivetrain.TurnToAngle;
import org.usfirst.frc.team1991.robot.intake.Intake;
import org.usfirst.frc.team1991.robot.shooter.PrepareForShotPosition;
import org.usfirst.frc.team1991.robot.shooter.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
// NOTE
// SHOOTER ANGLE ENCODER DOES NOT WORK
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static OI oi;
	public static Intake intake;
	public static Camera cam;

	public static PIDControllerConfiguration drivetrainTurnInPlace;
	public static PIDControllerConfiguration shooterUp;
	public static PIDControllerConfiguration shooterDown;
	public static PIDControllerConfiguration intakeUp;
	public static PIDControllerConfiguration intakeDown;
	public static PIDControllerConfiguration intakeUp1;
	public static PIDControllerConfiguration intakeDown1;
	Command autonomous;
	Command setShooterAngle;

	@Override
	public void robotInit() {
		RobotMap.init();

		//Init PID Controller
		readPreferences();

		//Init subststems
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		oi = new OI();
		intake = new Intake();
		cam = new Camera();
		autonomous = new StraightDrive(0.9, 8);
		setShooterAngle = new PrepareForShotPosition(ShotPositions.STOWED);
		//Dashboard values fro turnToAngle
		SmartDashboard.putData(new TurnToAngle(RobotMap.pref.get("Turn_To_Angle")));
		SmartDashboard.putNumber("Target Yaw", 0);
		SmartDashboard.putNumber("Angle_Turn_P", 0.04);
		SmartDashboard.putNumber("Angle_Turn_I", 0.006);
		SmartDashboard.putNumber("Angle_Turn_D", 0.115);


	}
	//Reads preferences and set values
	public void readPreferences(){
		RobotMap.pref.setValues();
		shooterUp = new PIDControllerConfiguration("Shooter_Up");
		shooterDown = new PIDControllerConfiguration("Shooter_Down");
		drivetrainTurnInPlace = new PIDControllerConfiguration("DriveTrain_Turn");
		//intakeUp = new PIDControllerConfiguration("Intake_Up");
		//intakeDown = new PIDControllerConfiguration("Intake_Down");
		//intakeUp1 = new PIDControllerConfiguration("Intake_Up1");
		//intakeDown1 = new PIDControllerConfiguration("Intake_Down1");

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
		readPreferences();
		RobotMap.navX.reset();
		cam.start("cam1");
		autonomous.start();
	}

	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Current Yaw", RobotMap.navX.getYaw());
		cam.refreshFrame();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		readPreferences();
		if (autonomous != null) autonomous.cancel();
		RobotMap.navX.reset();
		cam.start("cam1");
	}

	@Override
	public void teleopPeriodic() {
		cam.refreshFrame();
		SmartDashboard.putNumber("Yaw", RobotMap.navX.getYaw());
		SmartDashboard.putNumber("ENC0", RobotMap.angleEncoder.getAverageVoltage());
		SmartDashboard.putNumber("ENC1", RobotMap.intakeEncoder.getAverageVoltage());
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
