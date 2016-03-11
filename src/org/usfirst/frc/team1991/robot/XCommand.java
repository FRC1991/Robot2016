package org.usfirst.frc.team1991.robot;

import org.usfirst.frc.team1991.robot.subsystems.SwegSystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

// Extendable Command
public class XCommand extends Command {

  protected boolean finished = false;
  protected Subsystem system = null;
  protected boolean shouldDisableSystemAfterQuit = false;

  // Command constructor
  public XCommand(Subsystem system, boolean disableAfterQuit) {
    this.system = system;
    this.shouldDisableSystemAfterQuit = disableAfterQuit;
    requires(system);
  }

  public XCommand() {}

  // Called before the command is executed
  protected void initialize() {
    finished = false;
  }

  // If execute is not overridden, this method will run once and then the command will end
  protected void runOnce() {}

  // Called once after isFinished() returns true
  // If a subsystem was passed into the constructor, disable it
  protected void quit() {
    if (!shouldDisableSystemAfterQuit) return;
    if (system != null && (system instanceof SwegSystem)) {
      ((SwegSystem) system).disable();
    }
    else if (system != null && (system instanceof PIDSubsystem)) {
      ((PIDSubsystem) system).disable();
    }
  }

  // Note that if the command is run whileHeld, isFinished() will not get called and execute() will continue running until the button is let go
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