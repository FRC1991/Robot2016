package org.usfirst.frc.team1991.robot.commands;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class StraightDrive extends PIDCommand {

	double duration, turn, speed;
	
    public StraightDrive(double duration, double speed) {
    	super(1, 1, 1); // PID 1,1,1
    	this.duration = duration;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.getPIDController().setInputRange(-180, 180);
    	this.getPIDController().setOutputRange(-1, 1);
    	setSetpoint(0);
    	this.getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftSpeed = speed + turn;
    	double rightSpeed = speed - turn;
    	Robot.drivetrain.drive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.getPIDController().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.getPIDController().disable();
    }

	@Override
	protected double returnPIDInput() {
		return Robot.navX.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		turn = output;
		
	}
}
