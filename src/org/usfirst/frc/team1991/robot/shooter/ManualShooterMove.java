package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.control.*;

import edu.wpi.first.wpilibj.command.Command;

public class ManualShooterMove extends Command {


	public ManualShooterMove() {
		requires(Robot.shooter);
	}
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.shooter.move(BaseOI.aux.getController().getLJoystickY() * -0.5);
	}

	@Override
	protected boolean isFinished() {
		return false;
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
