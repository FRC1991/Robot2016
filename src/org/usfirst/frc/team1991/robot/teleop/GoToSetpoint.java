package org.usfirst.frc.team1991.robot.teleop;
import org.usfirst.frc.team1991.robot.XCommand;
import org.usfirst.frc.team1991.robot.subsystems.SwegSystem;
import org.usfirst.frc.team1991.robot.Robot;


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
