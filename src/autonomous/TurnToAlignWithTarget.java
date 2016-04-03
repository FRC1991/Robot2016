package src.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;

public class TurnToAlignWithTarget extends XCommand {
	
	private double differenceTolerance = 5;
	private double onTargetStartTime = 0;
	private double alignInterval = 1.00;
	private boolean onTarget = false;
	
	public TurnToAlignWithTarget() {
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		Robot.drivetrain.enable();
		align();
		setTimeout(6);
		System.out.println("Beginning auto-aim.");
	}
	
	private boolean differenceOnTarget() {
		double differenceInPixels = SmartDashboard.getNumber("Difference", 100);
		return (Math.abs(differenceInPixels) <= differenceTolerance);

	}
	
	private double timeSinceOnTarget() {
		return timeSinceInitialized() - onTargetStartTime;
	}
	
	private boolean aimedLongEnough() {
		return (onTarget && timeSinceOnTarget() > 2);
	}
	
	private void align() {
		double desiredYaw = SmartDashboard.getNumber("Target Yaw");
		Robot.drivetrain.setYawAndSpeed(desiredYaw, 0);
		System.out.println("Aligning to yaw: " + desiredYaw);
	}
	
	@Override
	protected void execute() {
		if (timeSinceInitialized() % alignInterval <= 0.2) {
			align();
		}
		if (differenceOnTarget()) {
			if (!onTarget) {
				onTarget = true;
				onTargetStartTime = timeSinceInitialized();
				System.out.println("On target");
			}	
		}
		else {
			onTarget = false;
		}
		
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		if (isTimedOut()) System.out.println("Timed out");
		if (aimedLongEnough()) System.out.println("Aimed long enough");
		return aimedLongEnough() || isTimedOut();
	}
}
