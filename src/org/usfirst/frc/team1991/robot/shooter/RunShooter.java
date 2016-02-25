package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class RunShooter extends Command {

	double leftSpeed;
	double rightSpeed;
	double duration;

    protected void initialize() {
    	this.leftSpeed = Robot.shooter.currentPosition.leftSpeed;
		this.rightSpeed = Robot.shooter.currentPosition.rightSpeed;
		this.duration = Robot.shooter.currentPosition.runTime;
    	setTimeout(duration);
    }

    protected void execute() {
    	Robot.shooter.run(leftSpeed, rightSpeed);
    	
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.shooter.stop();
    }

    protected void interrupted() {
    	Robot.shooter.stop();
    }
}
