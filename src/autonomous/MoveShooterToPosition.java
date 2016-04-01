package src.autonomous;
import src.Robot;
import src.XCommand;

public class MoveShooterToPosition extends XCommand {

  private Robot.Position desiredPosition;

  public MoveShooterToPosition(Robot.Position pos) {
    this.desiredPosition = pos;
    requires(Robot.shooter);
  }

  protected void execute() {
    Robot.shooter.setSetpoint(desiredPosition.setpoint);
    Robot.shooter.enable();
    finish();
  }
}
