package src.teleop;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import src.Robot;
import src.XCommand;
import src.autonomous.PowerUpShooter;

public class Shoot extends CommandGroup {
  public Shoot() {
    addSequential(new PowerUpShooter());
    addSequential(new XCommand() {
      public void execute() {
        Robot.shooter.feed(0.9);
        finish();
      }
    });
    addSequential(new WaitCommand(0.5));
  };

  protected void end() {
    Robot.shooter.disable();
  }

  protected void interrupted() {
    Robot.shooter.disable();
  }
}
