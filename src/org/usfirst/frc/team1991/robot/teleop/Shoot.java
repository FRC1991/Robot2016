package org.usfirst.frc.team1991.robot.teleop;

import org.usfirst.frc.team1991.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {

  boolean finished = false;

	protected void initialize() {}

	protected void execute() {
		Robot.shooter.run(0.6);
    if (timeSinceInitialized() > 2) {
      Robot.shooter.feed(0.8);
    }
    if (timeSinceInitialized() > 4) {
      finished = true;
    }
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void end() {
		Robot.shooter.disable();
	}

	protected void interrupted() {
		Robot.intake.disable();
	}
}
