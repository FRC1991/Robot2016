
package org.usfirst.frc.team1991.robot;

import org.usfirst.frc.team1991.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team1991.robot.drivetrain.StraightDrive;
import org.usfirst.frc.team1991.robot.drivetrain.TurnToAngle;
import org.usfirst.frc.team1991.robot.intake.Intake;
import org.usfirst.frc.team1991.robot.shooter.MoveShooter;
import org.usfirst.frc.team1991.robot.shooter.Shooter;
import org.usfirst.frc.team1991.robot.control.BaseOI;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static BaseOI oi;
	public static Intake intake;
	public static Camera cam;
	public static Command autonomous;

	@Override
	public void robotInit() {
		initialMessages();
		RobotMap.init();
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		intake = new Intake();
		cam = new Camera();
		oi = new BaseOI();
		autonomous = new StraightDrive(0.9, 8);
		// SmartDashboard.putNumber("Angle_Turn_P", 0.04);
		// SmartDashboard.putNumber("Angle_Turn_I", 0.006);
		// SmartDashboard.putNumber("Angle_Turn_D", 0.115);
		SmartDashboard.putNumber("P", 0.5);
		SmartDashboard.putNumber("I", 0.0);
		SmartDashboard.putNumber("D", 0.0);
	}

	public void initialMessages() {
		System.out.println("Shooter timeout is set to 5 seconds. Perhaps read from file instead. FireShooter");
		System.out.println("Shooter position is manually set to far shot. Shooter");
		System.out.println("isAtSpeed always returns true. FireShooter");
	}

	@Override
	public void disabledInit() {
		// Robot.shooter.stop();
		Robot.drivetrain.stop();
		Robot.intake.stop();
		//Robot.cam.stop();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		RobotMap.reloadPreferences();
		RobotMap.navX.reset();
		//cam.start("cam1");
		autonomous.start();
	}

	@Override
	public void autonomousPeriodic() {
		cam.refreshFrame();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		RobotMap.reloadPreferences();
		if (autonomous != null) autonomous.cancel();
		RobotMap.navX.reset();
		//cam.start("cam1");
	}

	@Override
	public void teleopPeriodic() {
		//cam.refreshFrame();
		SmartDashboard.putNumber("Shooter Encoder Voltage", RobotMap.shooter_angleEncoder.getAverageVoltage());
    SmartDashboard.putNumber("Shooter Encoder Scaled Voltage", Robot.shooter.encoderZero - RobotMap.shooter_angleEncoder.getAverageVoltage());
		// SmartDashboard.putNumber("Intake Encoder Voltage", RobotMap.intake_angleEncoder.getAverageVoltage());
		// SmartDashboard.putNumber("Intake Encoder Scaled Voltage", -Robot.intake.encoderZero + RobotMap.intake_angleEncoder.getAverageVoltage());
		// SmartDashboard.putBoolean("Limit Switch Value", RobotMap.intake_limitSwitch.get());
		// if (RobotMap.intake_limitSwitch.get() == false) {
		// 	Robot.intake.encoderZero = RobotMap.intake_angleEncoder.getAverageVoltage();
		// }
		SmartDashboard.putNumber("Zero", Robot.shooter.encoderZero);
		boolean zeroLimit = RobotMap.shooter_angleMotor.isFwdLimitSwitchClosed();
		if (zeroLimit == false) {
			Robot.shooter.encoderZero = RobotMap.shooter_angleEncoder.getAverageVoltage();
		}
		SmartDashboard.putBoolean("Forward Limit", zeroLimit);
		SmartDashboard.putBoolean("Back Limit", RobotMap.shooter_angleMotor.isRevLimitSwitchClosed());
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
