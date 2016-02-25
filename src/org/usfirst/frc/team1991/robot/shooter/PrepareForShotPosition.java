package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.PIDControl;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.ShotPositions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PrepareForShotPosition extends PIDControl {
	ShotPositions pos;
	
	public PrepareForShotPosition(ShotPositions pos) {
		super(Robot.shooterDown, pos.setPoint, RobotMap.angleEncoder, RobotMap.angleMotor);
		this.pos = pos;
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		if (pos.setPoint < Robot.shooter.currentPosition.setPoint) {
			Robot.shooterUp.configurePIDController(getPIDController());
    	}
    	else {
    		Robot.shooterDown.configurePIDController(getPIDController());
    	}
	}
	
	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		if (super.isFinished()) {
			Robot.shooter.currentPosition = this.pos;
		}
		SmartDashboard.putString("Current Position", Robot.shooter.currentPosition.name());
		return super.isFinished();
	}
	
}