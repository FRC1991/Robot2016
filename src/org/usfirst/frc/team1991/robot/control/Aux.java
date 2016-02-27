package org.usfirst.frc.team1991.robot.control;

import org.usfirst.frc.team1991.robot.intake.ManualIntakeMove;
import org.usfirst.frc.team1991.robot.shooter.ManualShooterMove;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Aux extends Driver {

  public Aux(int controllerPort) {
    super(controllerPort);
  }

  protected void registerButtons() {
    controller.X.toggleWhenPressed(new ManualIntakeMove());
    controller.B.toggleWhenPressed(new ManualShooterMove());
    controller.LBumper.whenPressed(new Feed());
    controller.RBumper.whenPressed(new Shoot());
  }

  // Get RJoystickY
  public double getY() {
    return controller.getRJoystickY();
  }

}
