package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AngleShooter extends PIDCommand {

	double angle;
	boolean finished = false;
	
    public AngleShooter(double p, double angle) {
    	super(p, 0, 0);
    	requires(Robot.shooter);
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setInputRange(0.872, 1.245);
    	getPIDController().setAbsoluteTolerance(0.02);
    	getPIDController().setContinuous(false);
    	setSetpoint(angle);
    	getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Encoder", Robot.shooter.angleEncoder.getAverageVoltage());
		SmartDashboard.putNumber("Setpoint", getSetpoint());
		SmartDashboard.putNumber("Error", getPIDController().getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(getPIDController().getError()) < 0.02){
    		SmartDashboard.putBoolean("Devastated", true);
    		return true;
    	}
    	SmartDashboard.putBoolean("Devastated", false);
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.shooter.angleEncoder.getAverageVoltage();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.shooter.angleMotor.set(output);
		
	}
}
