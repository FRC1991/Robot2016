package org.usfirst.frc.team1991.robot.teleop;

import java.util.HashMap;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class SetpointCommand extends MethodCommand {
  private PIDSubsystem system;
  private double setpoint;

  public SetpointCommand(PIDSubsystem system, double setpoint) {
    this.system = system;
    this.setpoint = setpoint;
  }

  public SetpointCommand(PIDSubsystem system, Robot.Position pos) {
    this(system, pos.setpoint);
  }

  public void call() {
    system.setSetpoint(setpoint);
    system.enable();
  }
}
