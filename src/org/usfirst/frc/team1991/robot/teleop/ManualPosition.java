package org.usfirst.frc.team1991.robot.teleop;

import org.usfirst.frc.team1991.robot.subsystems.SwegSystem;
import edu.wpi.first.wpilibj.command.Command;

public abstract class ManualPosition extends XCommand {

  public ManualPosition(SwegSystem system) {
    super(system, true);
  }

  protected abstract double getSpeed();

  protected abstract void useOutput(double output);

  protected void execute() {
    useOutput(getSpeed());
  }
}
