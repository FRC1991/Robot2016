package org.usfirst.frc.team1991.robot.teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class ManualPosition extends XCommand {

  public ManualPosition(PIDSubsystem system) {
    super(system);
  }
  
  public double getSpeed() {
	  // Return a double speed
	  return 0.0;
  }

  protected void move(double speed) {
	  // Move a motor or something here
    return;
  }

  protected void execute() {
    move(getSpeed());
  }

  protected boolean isFinished() {
    return false;
  }
}
