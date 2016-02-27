package org.usfirst.frc.team1991.robot.control;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Feed extends Command {

	public Feed() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.intake.feed(RobotMap.intakeFeedSpeed);
		Robot.shooter.feed(RobotMap.shooterFeedSpeed);
	}

	@Override
	protected boolean isFinished() {
		return Robot.shooter.ballPresent();

	}

	@Override
	protected void end() {
		Robot.intake.stop();
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		Robot.intake.stop();
		Robot.shooter.stop();
	}

}
