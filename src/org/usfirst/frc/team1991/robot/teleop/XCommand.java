package org.usfirst.frc.team1991.robot.teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

// Extendable Command
public class XCommand extends Command {

  protected boolean finished = false;
  protected Subsystem system = null;

  // Command constructor
  public XCommand(Subsystem system) {
    requires(system);
  }

  public XCommand() {}

  // Called before the command is executed
  protected void initialize() {}

  // If execute is not overridden, this method will run once and then the command will end
  protected void runOnce() {}

  // Called once after isFinished() returns true
  // If a subsystem was passed into the constructor, disable it
  protected void quit() {
    if (system != null && (system instanceof PIDSubsystem)) {
      ((PIDSubsystem)system).disable();
    }
  }

  protected void execute() {
    runOnce();
    finished = true;
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    quit();
  }

  protected void interrupted() {
    quit();
  }
}
