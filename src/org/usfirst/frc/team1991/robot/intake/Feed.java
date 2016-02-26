package org.usfirst.frc.team1991.robot.intake;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Feed extends Command {

	@Override
	protected void initialize() {
		requires(Robot.intake);
	}

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
	}

	@Override
	protected void interrupted() {
		Robot.intake.stop();
	}

}
