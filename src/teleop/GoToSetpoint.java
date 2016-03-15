package src.teleop;
import src.Robot;
import src.XCommand;
import src.subsystems.SwegSystem;


public class GoToSetpoint extends XCommand {
  private double setpoint;

  public GoToSetpoint(SwegSystem system, double setpoint) {
    super(system, false);
    this.setpoint = setpoint;
  }

  public GoToSetpoint(SwegSystem system, Robot.Position pos) {
    this(system, pos.setpoint);
  }

  protected void runOnce() {
    ((SwegSystem) system).setSetpoint(setpoint);
    ((SwegSystem) system).enable();
  }
}
