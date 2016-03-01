package org.usfirst.frc.team1991.robot.teleop;

import java.util.HashMap;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;


public class Drive extends Command {
  private XboxController driver;

  public Drive() {
    requires(Robot.drivetrain);
  }

  protected void initialize() {
    driver = Robot.driver;
  }

  protected void execute() {
    double leftSpeed, rightSpeed, leftTrigger, rightTrigger;
    leftSpeed = driver.getLJoystickY();
    rightSpeed = driver.getRJoystickY();
    // Handle rotation
    if (driver.areTriggersPressed()) {
      leftTrigger = driver.getLTrigger();
      rightTrigger = driver.getRTrigger();
      if (leftTrigger > 0) {
        leftSpeed = -leftTrigger;
        rightSpeed = -leftSpeed;
      }
      else {
        rightSpeed = -rightTrigger;
        leftSpeed = -rightSpeed;
      }
      leftSpeed *= Robot.get("Drivetrain_Speed_Rotation");
      rightSpeed *= Robot.get("Drivetrain_Speed_Rotation");
    }
    Robot.drivetrain.drive(leftSpeed, rightSpeed);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    Robot.drivetrain.disable();
  }

  protected void interrupted() {
    Robot.drivetrain.disable();
  }
}
