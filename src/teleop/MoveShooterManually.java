package src.teleop;
import src.XCommand;
import src.Robot;

public class MoveShooterManually extends XCommand {

  public MoveShooterManually() {
    requires(Robot.shooter);
  }
  
  protected void initialize() {
	  Robot.shooter.disable();
  }

  protected void execute() {
    Robot.shooter.move(Robot.aux.getLJoystickY() * 0.6);
  }

  protected void quit(boolean wasInterrupted) {
    Robot.shooter.disable();
  }
}
