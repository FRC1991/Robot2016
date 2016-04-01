package src.autonomous;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;

public class TurnToAlignWithTarget2 extends XCommand {
	
	private double differenceTolerance = 8;
	private double onTargetStartTime = 0;
	private double alignInterval = 1.00;
	private boolean onTarget = false;
	
	public TurnToAlignWithTarget2() {
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		align();
	}
	
	private boolean differenceOnTarget() {
		double differenceInPixels = SmartDashboard.getNumber("Difference", 100);
		return (Math.abs(differenceInPixels) <= differenceTolerance);

	}

	
	private void align() {
		
	}
	
	@Override
	protected void execute() {
		if (timeSinceInitialized() % alignInterval <= 0.2) {
			align();
		}
		
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		return false;
	}
}
