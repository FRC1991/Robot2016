package src.autonomous;

import src.Robot;
import src.XCommand;

public class TurnToYaw extends XCommand {

	private double desiredYaw;
	
	public TurnToYaw(double desiredYaw) {
		this.desiredYaw = desiredYaw;
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		System.out.println("Turning to yaw: " + desiredYaw);
		Robot.drivetrain.setYawAndSpeed(desiredYaw, 0, true);
		Robot.drivetrain.enable();
	}
	
	@Override
	protected void execute() {
	}
	
//	public boolean isOnTarget(){
//		System.out.println(desiredYaw);
//		if(Robot.drivetrain.getNavX().getYaw() < desiredYaw + Robot.drivetrain.tolerance && Robot.drivetrain.getNavX().getYaw() > desiredYaw - Robot.drivetrain.tolerance){
//			return true;
//		}else{
//			return false;
//		}
//	}
	
	protected void quit(boolean wasInterrupted) {
		Robot.drivetrain.disable();
	}
	
	public boolean isFinished() {
		if (Robot.drivetrain.onTarget()) {
			System.out.println("Reached yaw of " + desiredYaw + " - Error: " + Robot.drivetrain.getPIDController().getError());
		}
		return Robot.drivetrain.onTarget();
	}
}
