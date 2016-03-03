package org.usfirst.frc.team1991.robot.teleop;

import org.usfirst.frc.team1991.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class GoToSetpoint extends XCommand {
  private double setpoint;

  public GoToSetpoint(PIDSubsystem system, double setpoint) {
    super(system);
    this.setpoint = setpoint;
  }

  public GoToSetpoint(PIDSubsystem system, Robot.Position pos) {
    this(system, pos.setpoint);
  }

  protected void runOnce() {
    ((PIDSubsystem) system).setSetpoint(setpoint);
    ((PIDSubsystem) system).enable();
  }

  protected boolean isFinished() {
    return false;
  }
}
