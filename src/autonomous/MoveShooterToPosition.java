package src.autonomous;
import src.Robot;
import src.XCommand;

public class MoveShooterToPosition extends XCommand {

  private Robot.Position desiredPosition;
  private boolean shouldWait = false;

  public MoveShooterToPosition(Robot.Position pos) {
    this.desiredPosition = pos;
    requires(Robot.shooter);
  }
  
  public MoveShooterToPosition(Robot.Position pos, boolean shouldWait) {
	  this(pos);
	  this.shouldWait = shouldWait;
  }

  protected void initialize() {
    Robot.shooter.setSetpoint(desiredPosition.setpoint);
    Robot.shooter.enable();
    finish();
  }

  @Override
  protected void execute() {
  }
  
  protected boolean isFinished() {
	  if (shouldWait) return Robot.shooter.onPoint();
	  return isFinished();
  }
}
