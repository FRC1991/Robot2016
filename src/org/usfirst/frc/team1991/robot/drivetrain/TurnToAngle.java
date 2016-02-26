package org.usfirst.frc.team1991.robot.drivetrain;

import org.usfirst.frc.team1991.robot.PIDControl;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends PIDControl {
	double turn;

	public TurnToAngle(double angle) {
		super(RobotMap.drivetrain_TurnInPlace, angle, null, null);
		requires(Robot.drivetrain);

	}

	@Override
	protected void initialize() {
		//RobotMap.navX.zeroYaw();

		pid.setPID(SmartDashboard.getNumber("Angle_Turn_P"),
				SmartDashboard.getNumber("Angle_Turn_I"),
				SmartDashboard.getNumber("Angle_Turn_D"));

	}

	@Override
	protected void execute() {
		double speed = 0;
		double leftSpeed = speed + turn;
    	double rightSpeed = speed - turn;
    	Robot.drivetrain.drive(leftSpeed, rightSpeed);
	}

	@Override
	protected double returnPIDInput() {
		return RobotMap.navX.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		this.turn = output;
	}
}
