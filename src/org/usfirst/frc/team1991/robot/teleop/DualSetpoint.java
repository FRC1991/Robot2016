package org.usfirst.frc.team1991.robot.teleop;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1991.robot.teleop.GoToSetpoint;
import org.usfirst.frc.team1991.robot.Robot;

public class DualSetpoint extends CommandGroup {

  public DualSetpoint(Robot.Position shooterPosition, Robot.Position intakePosition) {
    addSequential(new GoToSetpoint(Robot.shooter, shooterPosition));
    addSequential(new GoToSetpoint(Robot.intake, intakePosition));
  }
}
