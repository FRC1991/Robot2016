// package org.usfirst.frc.team1991.robot.intake;
//
// import org.usfirst.frc.team1991.robot.PIDControl;
// import org.usfirst.frc.team1991.robot.Robot;
// import org.usfirst.frc.team1991.robot.RobotMap;
// import org.usfirst.frc.team1991.robot.ShotPositions;
//
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
// /**
//  *
//  */
// public class IntakeAngle extends PIDControl {
// 	ShotPositions pos;
//
// 	public IntakeAngle(ShotPositions pos) {
// 		super(Robot.intakeDown, 0.0, RobotMap.intakeEncoder, RobotMap.intakeMotorL);
// 		this.pos = pos;
// 		requires(Robot.intake);
// 	}
//
// 	@Override
// 	protected void initialize() {
// 		/*if (pos.setPoint < Robot.intake.currentPosition.setPoint) {
// 			Robot.intakeUp.configurePIDController(getPIDController());
//     	}
//     	else {
//     		Robot.intakeDown.configurePIDController(getPIDController());
//     	}*/
// 	}
//
// 	@Override
// 	protected void execute() {
// 	}
//
// 	@Override
// 	protected boolean isFinished() {
// 		if (super.isFinished()) {
// 			Robot.intake.currentPosition = this.pos;
// 		}
// 		SmartDashboard.putString("Current Position", Robot.intake.currentPosition.name());
// 		return super.isFinished();
// 	}
//
// }
