package org.usfirst.frc.team1991.robot.drivetrain;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StraightDrive extends PIDCommand {

	double duration, turn, speed;
	
    public StraightDrive(double speed, double duration) {
    	super(0.05, 0, 0.05); 
    	this.duration = duration;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.navX.reset();
    	setSetpoint(0);
    	this.getPIDController().enable();
    	setTimeout(duration);
    	Robot.drivetrain.reverse = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = .7;
		double leftSpeed = speed + turn;
    	double rightSpeed = speed + turn;
    	Robot.drivetrain.drive(-leftSpeed, -rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.getPIDController().disable();
    	Robot.drivetrain.reverse = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.getPIDController().disable();
    	Robot.drivetrain.reverse = false;
    }

	@Override
	protected double returnPIDInput() {
		return RobotMap.navX.getYaw();
	}

	@Override
	protected void usePIDOutput(double output) {
		turn = output;
		
	}
}
