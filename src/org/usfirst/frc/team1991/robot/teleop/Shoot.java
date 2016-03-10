package org.usfirst.frc.team1991.robot.teleop;

import org.usfirst.frc.team1991.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Shoot extends CommandGroup {
  public Shoot() {
    addSequential(new XCommand() {
      public void runOnce() {
        Robot.shooter.run(0.9);
      }
    });
    addSequential(new WaitCommand(2));
    addSequential(new XCommand() {
      public void runOnce() {
        Robot.shooter.feed(0.8);
      }
    });
    addSequential(new WaitCommand(1));
  };

  protected void end() {
    Robot.shooter.disable();
  }

  protected void interrupted() {
    Robot.shooter.disable();
  }
}
