package src.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;

public class DistanceReckon extends XCommand {
	
	private final double MIN_RANGE = 7.5;
	private final double MAX_RANGE = 11.0;
	private final double SPEED = 0.3;
	private final double ALIGN_INTERVAL = 1.0;
	private double currentDistance;
	
	public DistanceReckon() {
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		currentDistance = SmartDashboard.getNumber("Distance", 0);
		if (timeSinceInitialized() % ALIGN_INTERVAL <= 0.2) {
			alignDistance();
		}
		
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		return onDesiredRange();
	}
	
	protected void alignDistance(){
		if(!onDesiredRange()){
			if(currentDistance < MIN_RANGE) Robot.drivetrain.drive(-SPEED, -SPEED);
			if(currentDistance > MAX_RANGE) Robot.drivetrain.drive(SPEED, SPEED);
		}
	}
	
	protected boolean onDesiredRange(){
		if(currentDistance != 0){
			return(currentDistance > MIN_RANGE && currentDistance < MAX_RANGE);
		}else{
			return false;
		}
	}
}
