package src.teleop;
import src.XCommand;
import src.Robot;

public class MoveIntakeManually extends XCommand {

  public MoveIntakeManually() {
    requires(Robot.intake);
  }

  protected void execute() {
    Robot.intake.move(Robot.aux.getRJoystickY() * 0.6);
  }

  protected void quit(boolean wasInterrupted) {
    Robot.intake.disable();
  }
}
