package src.teleop;
import edu.wpi.first.wpilibj.command.CommandGroup;
import src.Robot;

public class DualSetpoint extends CommandGroup {

	public DualSetpoint(Robot.Position shooterPosition, Robot.Position intakePosition) {
		addSequential(new GoToSetpoint(Robot.intake, intakePosition));
		addSequential(new WaitCommand(1));
		addSequential(new GoToSetpoint(Robot.shooter, shooterPosition));
	}
}
