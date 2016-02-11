package org.usfirst.frc.team1991.robot.commands;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class RunShooter extends Command {

	double leftSpeed;
	double rightSpeed;
	double duration;
	
	public RunShooter(double leftSpeed, double rightSpeed, double duration) {
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
		this.duration = duration;
	}
    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.run(leftSpeed, rightSpeed);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
