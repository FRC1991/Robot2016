package src.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;

public class TurnToAlignWithTarget extends XCommand {
	
	public TurnToAlignWithTarget() {
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		double desiredYaw = SmartDashboard.getNumber("Target Yaw");
		Robot.drivetrain.setYawAndSpeed(desiredYaw, 0);
		Robot.drivetrain.enable();
	}
	
	@Override
	protected void execute() {
	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	protected boolean isFinished() {
		return Robot.drivetrain.onTarget();
	}
}
