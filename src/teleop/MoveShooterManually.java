package src.teleop;
import src.XCommand;
import src.Robot;

public class MoveShooterManually extends XCommand {

  public MoveShooterManually() {
    requires(Robot.shooter);
  }

  protected void execute() {
    Robot.shooter.move(Robot.aux.getRJoystickY() * 0.6);
  }

  protected void quit(boolean wasInterrupted) {
    Robot.shooter.disable();
  }
}
