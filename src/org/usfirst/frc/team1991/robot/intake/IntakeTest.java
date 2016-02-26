package org.usfirst.frc.team1991.robot.intake;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeTest extends Command {


	public IntakeTest(){
		requires(Robot.intake);
	}
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.intake.move(Robot.oi.joy.getRawAxis(5) * 0.05);

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
