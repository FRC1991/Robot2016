package org.usfirst.frc.team1991.robot.teleop;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.teleop.GoToSetpoint;

public class DualSetpoint extends CommandGroup {

	public DualSetpoint(Robot.Position shooterPosition, Robot.Position intakePosition) {
		addSequential(new GoToSetpoint(Robot.intake, intakePosition));
		addSequential(new WaitCommand(1));
		addSequential(new GoToSetpoint(Robot.shooter, shooterPosition));
	}
}
