package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.Position;
import edu.wpi.first.wpilibj.command.Command;


public class WaitForShooterSpeed extends Command {

	protected void initialize() {
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Robot.shooter.isAtSpeed();
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
