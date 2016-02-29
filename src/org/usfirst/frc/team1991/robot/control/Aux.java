package org.usfirst.frc.team1991.robot.control;

import org.usfirst.frc.team1991.robot.intake.ManualIntakeMove;
import org.usfirst.frc.team1991.robot.shooter.MoveShooter;
import org.usfirst.frc.team1991.robot.Position;
import org.usfirst.frc.team1991.robot.shooter.ManualShooterMove;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Aux extends Driver {

  public Aux(int controllerPort) {
    super(controllerPort);
  }

  protected void registerButtons() {
    controller.A.whenPressed(new MoveShooter(Position.SHOOTER_STOWED));
    controller.Y.whenPressed(new MoveShooter(Position.SHOOTER_BARF));
    controller.X.whenPressed(new MoveShooter(Position.SHOOTER_FEED));
    controller.LJoystick.whileHeld(new ManualShooterMove());
    controller.RJoystick.whileHeld(new ManualIntakeMove());
    controller.LBumper.whenPressed(new Feed());
    controller.RBumper.whenPressed(new Shoot());
  }

  public XboxController getController() {
    return controller;
  }

}
