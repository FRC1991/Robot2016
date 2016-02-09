
package org.usfirst.frc.team1991.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1991.robot.Robot;

/**
 *
 */
public class TankDrive extends Command {
	
	Joystick gamepad;

    public TankDrive() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.gamepad = Robot.oi.joy;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double right = Robot.oi.joy.getRawAxis(1);
    	double left = Robot.oi.joy.getRawAxis(5);
    
    	// Send in values to tank drive
    	Robot.drivetrain.drive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
