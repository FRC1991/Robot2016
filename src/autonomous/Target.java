//package src.autonomous;
//
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import src.Robot;
//
//public class Target {
//	
//	private boolean beenOnTarget = false;
//	private long beenOnTargetStartTime = 0;
//	private double differenceTolerance = 8;
//	
//	public Target() {
//	}
//	
//	public double getTargetYaw() {
//		return SmartDashboard.getNumber("Target Yaw", Robot.drivetrain.getNavX().getYaw());
//	}
//	
//	public boolean currentlyOnTarget() {
//		double difference = Math.abs(SmartDashboard.getNumber("Difference", 100));
//		boolean newOnTarget = (difference < differenceTolerance);
//		return newOnTarget;
//	}
//	
//	public boolean onTarget() {
//		
//	}
//	
//	
//}
