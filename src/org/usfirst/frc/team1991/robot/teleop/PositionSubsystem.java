package org.usfirst.frc.team1991.robot.teleop;

import java.util.HashMap;
import org.usfirst.frc.team1991.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PositionSubsystem extends Command {
  private XboxController aux;
  private PIDSubsystem system; // Subsystem being manipulated
  private double setpoint;
  private int joystickAxis;

  public PositionSubsystem(PIDSubsystem system, int joystickAxis) {
    this.system = system;
    this.joystickAxis = joystickAxis;
    requires(system);
  }

  protected void initialize() {
    aux = Robot.aux;
    // Get starting position
    setpoint = system.getPosition();
    system.enable();
  }

  protected void execute() {
    setpoint += aux.axis(joystickAxis) * 0.05;
    system.setSetpoint(setpoint);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    system.disable();
  }

  protected void interrupted() {
    system.disable();
  }
}
