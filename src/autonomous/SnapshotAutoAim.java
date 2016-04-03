package src.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;

public class SnapshotAutoAim extends XCommand {

	private TurnToYaw turn = null;
	private double differenceTolerance = 3;

	protected void initialize() {
		super.initialize();
		System.out.println("Starting SnapshotAutoAim.");
	}
	
	private double getTargetYaw() {
		return SmartDashboard.getNumber("Target Yaw", Robot.drivetrain.getPosition());
	}
	
	private void aim() {
		double targetYaw = getTargetYaw();
		System.out.println("Turning to target yaw: " + targetYaw);
		turn = new TurnToYaw(targetYaw);
		Scheduler.getInstance().add(turn);
	}
	
	private boolean differenceOnTarget() {
		double differenceInPixels = SmartDashboard.getNumber("Difference", 100);
		return (Math.abs(differenceInPixels) <= differenceTolerance);

	}

	@Override
	protected void execute() {
		if (differenceOnTarget() || !Robot.driver.isButtonPressed(4)) {
			System.out.println("On target with difference of: " +  SmartDashboard.getNumber("Difference", 100));
			turn = null;
			finish();
		}
		else if (turn == null || turn.isFinished()) {
			aim();
			System.out.println("Aiming");
		}
	}
}
