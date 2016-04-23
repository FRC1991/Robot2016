package src.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;

public class SnapshotAutoAim extends XCommand {

	private TurnToYaw turn = null;
	private int times = 0;
	
	protected void initialize() {
		super.initialize();
		times = 0;
		if (turn != null) {
			turn.cancel();
			turn = null;
		}
		System.out.println("Starting SnapshotAutoAim.");
	}
	
	private double getTargetYaw() {
		return SmartDashboard.getNumber("Target Yaw", Robot.drivetrain.getPosition());
	}
	
	private void aim() {
		double targetYaw = getTargetYaw();
		turn = new TurnToYaw(targetYaw);
		Scheduler.getInstance().add(turn);
	}

	@Override
	protected void execute() {
		if (turn == null) {
			times += 1;
			System.out.println("Starting turn number " + times);
			aim();
		}
		else if (turn.isFinished()) {
			System.out.println("Finished turning with difference of: " +  SmartDashboard.getNumber("Difference", 100));
			turn = null;
			if (times >= 2) {
				System.out.println("SnapshotAutoAim finished");
				finish();
			}
		}
	}
}
