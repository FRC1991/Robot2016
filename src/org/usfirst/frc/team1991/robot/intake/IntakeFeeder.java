package org.usfirst.frc.team1991.robot.intake;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeFeeder extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		Robot.intake.feed(0.5);

	}

	@Override
	protected boolean isFinished() {
		return Robot.shooter.ballPresent;

	}

	@Override
	protected void end() {
		Robot.intake.stop();

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
