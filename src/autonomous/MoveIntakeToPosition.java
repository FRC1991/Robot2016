package src.autonomous;
import src.Robot;
import src.XCommand;

public class MoveIntakeToPosition extends XCommand {

  private Robot.Position desiredPosition;
  private boolean shouldWait = false;

  public MoveIntakeToPosition(Robot.Position pos) {
    this.desiredPosition = pos;
    requires(Robot.intake);
  }
  
  public MoveIntakeToPosition(Robot.Position pos, boolean shouldWait) {
	  this(pos);
	  this.shouldWait = shouldWait;
  }
  
  protected void initialize() {
    Robot.intake.setSetpoint(desiredPosition.setpoint);
    Robot.intake.enable();
    finish();
  }
  
  @Override
  protected void execute() {
  }
  
  protected boolean isFinished() {
	  if(shouldWait) return Robot.shooter.onPoint();
	  return isFinished();
  }
  
}
