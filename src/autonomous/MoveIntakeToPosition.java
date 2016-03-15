package src.autonomous;
import src.Robot;
import src.XCommand;

public class MoveIntakeToPosition extends XCommand {

  private Robot.Position desiredPosition;

  public MoveIntakeToPosition(Robot.Position pos) {
    this.desiredPosition = pos;
    requires(Robot.intake);
  }

  protected void execute() {
    Robot.intake.setSetpoint(desiredPosition.setpoint);
    Robot.intake.enable();
    finish();
  }
}
