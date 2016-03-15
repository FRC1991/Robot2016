package src.teleop;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import src.Robot;
import src.XCommand;

public class Shoot extends CommandGroup {
  public Shoot() {
    addSequential(new XCommand() {
      protected void execute() {
        Robot.shooter.run(0.9);
        finish();
      }
    });
    addSequential(new WaitCommand(2));
    addSequential(new XCommand() {
      public void execute() {
        Robot.shooter.feed(0.8);
        finish();
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
