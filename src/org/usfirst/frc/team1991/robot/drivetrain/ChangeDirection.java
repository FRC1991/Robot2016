
package org.usfirst.frc.team1991.robot.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1991.robot.Robot;

/**
 * Toggles the direction of the drivetrain.
 */
public class ChangeDirection extends Command {

		boolean finished = false;

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.reverse = !Robot.drivetrain.reverse;
		finished = true;

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
