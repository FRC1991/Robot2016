package org.usfirst.frc.team1991.robot.intake;

import org.usfirst.frc.team1991.robot.PIDControl;
import org.usfirst.frc.team1991.robot.Position;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveIntake extends PIDControl {
	Position pos;

	public MoveIntake(Position pos) {
		super(RobotMap.intake, pos.setPoint, RobotMap.intake_angleEncoder, null);
		this.pos = pos;
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double p = SmartDashboard.getNumber("P");
		double i = SmartDashboard.getNumber("I");
		double d = SmartDashboard.getNumber("D");
		this.pid.setPID(p, i, d);
	}

	@Override
	protected double returnPIDInput() {
		return -Robot.intake.encoderZero + this.encoder.getAverageVoltage();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.intake.move(output);
	}

}
