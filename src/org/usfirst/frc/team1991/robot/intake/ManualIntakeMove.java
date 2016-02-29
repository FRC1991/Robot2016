package org.usfirst.frc.team1991.robot.intake;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.control.*;

import edu.wpi.first.wpilibj.command.Command;

public class ManualIntakeMove extends Command {


	public ManualIntakeMove() {
		requires(Robot.intake);
	}
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.intake.move(BaseOI.aux.getController().getRJoystickY() * -0.5);
	}

	@Override
	protected boolean isFinished() {
		return false;
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
