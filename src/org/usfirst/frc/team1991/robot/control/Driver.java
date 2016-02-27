package org.usfirst.frc.team1991.robot.control;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Driver {
  protected XboxController controller;

  protected Driver(int controllerPort) {
    controller = new XboxController(controllerPort);
    registerButtons();
  }

  protected void registerButtons() {
    controller.Start.whenPressed(new ReverseDrive());
  }

  // Called manually by ManualDrive command
  public double[] getSpeeds() {
    // Tank Drive
    return new double[]{controller.getLJoystickY(), controller.getRJoystickY()};
  }

  public boolean triggersPressed() {
    return controller.areTriggersPressed();
  }

  public double[] getTriggerValues() {
    return new double[]{controller.getLTrigger(), controller.getRTrigger()};
  }

}
