package org.usfirst.frc.team1991.robot.commands;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {

	double left, right, duration;
	
    public AutoDrive(double left, double right, double duration) {
    	requires(Robot.drivetrain);
        this.left = left;
        this.right = right;
        this.duration = duration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(duration);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(left * -1,  right * -1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
