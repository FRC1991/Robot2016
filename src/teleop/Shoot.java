package src.teleop;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import src.Robot;
import src.XCommand;

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
