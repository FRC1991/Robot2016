package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.PIDControl;
import org.usfirst.frc.team1991.robot.Position;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveShooter extends PIDControl {
	Position pos;

	public MoveShooter(Position pos) {
		super(RobotMap.shooter_Up, pos.setPoint, RobotMap.shooter_angleEncoder, RobotMap.shooter_angleMotor);
		this.pos = pos;
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		if (pos.setPoint > Robot.shooter.getCurrentPosition().setPoint) {
			RobotMap.shooter_Up.configurePIDController(getPIDController());
			SmartDashboard.putString("Position", "Up");
		}
		else {
			RobotMap.shooter_Down.configurePIDController(getPIDController());
			SmartDashboard.putString("Position", "Down");
		}
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		if (super.isFinished()) {
			System.out.println("Finished");
			Robot.shooter.setCurrentPosition(pos);
			return true;
		}
		System.out.println("Error: " + this.pid.getError());
		return false;
	}

	@Override
	protected double returnPIDInput() {
		return Robot.shooter.encoderZero - RobotMap.shooter_angleEncoder.getAverageVoltage();
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.println("Power: " + output *-1);
		Robot.shooter.move(output * -1);
	}


	@Override
	protected void end() {
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		Robot.shooter.stop();
	}

}
