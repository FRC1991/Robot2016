
package org.usfirst.frc.team1991.robot.drivetrain;

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
    	
    	// Go completely forward if right bumper is held
    	if (gamepad.getRawButton(6)) {
    		left = -1;
    		right = -1;
    	}
    	// Go completely backward if right bumper is held
    	if (gamepad.getRawButton(5)) {
    		left = 1;
    		right = 1;
    	}
    	// Turn left or right with a variable speed if left/right trigger is held
    	if(gamepad.getRawAxis(2) > 0){
    		left = -gamepad.getRawAxis(2);
    		right = -left;
    	}
    	if(gamepad.getRawAxis(3) > 0){
    		right = -gamepad.getRawAxis(3);
    		left = -right;
    	}
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
