package src;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import src.subsystems.SwegSystem;

public abstract class XCommand extends Command {
  private boolean finished = false;

  public XCommand() {}

  protected void initialize() {
    finished = false;
  }

  protected abstract void execute();

  protected void quit(boolean wasInterrupted) {}

  protected void finish() {
    finished = true;
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    quit(false);
  }

  protected void interrupted() {
    quit(true);
  }

}