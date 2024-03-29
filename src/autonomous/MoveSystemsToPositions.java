package src.autonomous;
import src.Robot;
import src.XCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
public class MoveSystemsToPositions extends CommandGroup {

  public MoveSystemsToPositions(Robot.Position intakePos, Robot.Position shooterPos) {
    addSequential(new MoveIntakeToPosition(intakePos));
    addSequential(new WaitCommand(0.5));
    addSequential(new MoveShooterToPosition(shooterPos));
  }

}
