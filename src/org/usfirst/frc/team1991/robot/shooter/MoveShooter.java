package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.PIDControl;
import org.usfirst.frc.team1991.robot.Position;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveShooter extends PIDControl {
	Position pos;

	public MoveShooter(Position pos) {
		super(RobotMap.shooter_Down, pos.setPoint, RobotMap.shooter_angleEncoder, RobotMap.shooter_angleMotor);
		this.pos = pos;
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		if (pos.setPoint < Robot.shooter.getCurrentPosition().setPoint) {
			RobotMap.shooter_Up.configurePIDController(getPIDController());
		}
		else {
			RobotMap.shooter_Down.configurePIDController(getPIDController());
		}
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		if (super.isFinished()) {
			Robot.shooter.setCurrentPosition(pos);
			return true;
		}
		return false;
	}

}
