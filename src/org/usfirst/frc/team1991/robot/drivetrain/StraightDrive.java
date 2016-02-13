package org.usfirst.frc.team1991.robot.drivetrain;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StraightDrive extends PIDCommand {

	double duration, turn, speed;
	
    public StraightDrive(double speed, double duration) {
    	super(1, 0, 0); // PID 1,1,1
    	this.duration = duration;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.getPIDController().setInputRange(-180, 180);
    	this.getPIDController().setOutputRange(-1, 1);
    	setSetpoint(Robot.navX.getYaw());
    	this.getPIDController().enable();
    	setTimeout(duration);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Turn", turn);
    	double leftSpeed = speed + turn;
    	double rightSpeed = speed - turn;
    	SmartDashboard.putNumber("leftSpeed", leftSpeed);
    	SmartDashboard.putNumber("rightSpeed", rightSpeed);
    	Robot.drivetrain.drive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Elapsed Time", timeSinceInitialized());
    	SmartDashboard.putBoolean("Timed Out", isTimedOut());
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
		SmartDashboard.putNumber("PID Output", output);
		turn = output;
		
	}
}
