package org.usfirst.frc.team1991.robot.teleop;

import java.util.HashMap;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.concurrent.Callable;

// A command that runs a method once and ends
public class MethodCommand extends Command {

  boolean finished = false;

  protected void initialize() {}

  protected void call() {}

  protected void execute() {
    call();
    finished = true;
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
