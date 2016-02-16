package org.usfirst.frc.team1991.robot.drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1991.robot.*;

/**
 * A multifunction command meant to be used to control the drivetrain.
 */
public class Drive extends Command {
  Joystick gamepad;
  public enum ManualDriveModes {ARCADE, TANK};
  boolean autonomous = false;
  ManualDriveModes mode;
  double leftSpeed;
  double rightSpeed;
  double duration;

  /**
   * Run the left and right side at the specified speed for the specified duration (in seconds) and then end.
   */
  public Drive(double leftSpeed, double rightSpeed, double duration) {
    requires(Robot.drivetrain);
    this.autonomous = true;
    this.duration = duration;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
  }

  /**
   * Manually operate the drivetrain using an Xbox controller and the specified drive mode.
   */
  public Drive(ManualDriveModes mode, Joystick gamepad) {
    requires(Robot.drivetrain);
    this.gamepad = gamepad;
    this.mode = mode;
  }

  protected void initialize() {
    if (autonomous) {
      setTimeout(this.duration);
    }
  }

  /**
   * Just a helper function to return the raw axis value of the gamepad.
   */
  private double axis(int axis) {
    return this.gamepad.getRawAxis(axis);
  }

  // The next three methods all change the values of leftSpeed and rightSpeed and are used for manual robot control

  private void tankDrive() {
    leftSpeed = axis(5); // Y axis of left stick
    rightSpeed = axis(1); // Y axis of right stick
  }

  private void arcadeDrive() {
    double x = axis(0) * RobotMap.ARCADE_TURN_SENSITIVITY;
    double y = axis(1);
    leftSpeed = y + x;
    rightSpeed = y - x;
  }

  // Turn left or right on the spot using triggers
  private void rotatoPotato() {
    double leftTrigger = axis(2);
    double rightTrigger = axis(3);
    if (leftTrigger > 0) {
      leftSpeed = -leftTrigger;
      rightSpeed = -leftSpeed;
    }
    else {
      rightSpeed = -rightTrigger;
      leftSpeed = -rightSpeed;
    }
    leftSpeed *= RobotMap.DRIVETRAIN_ROTATION_MULTIPLIER;
    rightSpeed *= RobotMap.DRIVETRAIN_ROTATION_MULTIPLIER;
  }

  protected void execute() {
    // Set leftSpeed and rightSpeed manually using joystick input if not autonomous
    if (!autonomous) {
      boolean triggerPressed = (axis(2) > 0 || axis(3) > 0) ? true : false;
      if (this.mode == ManualDriveModes.TANK) {
        tankDrive();
      }
      else if (this.mode == ManualDriveModes.ARCADE) {
        arcadeDrive();
      }
      // Handle trigger buttons
      if (triggerPressed) {
        rotatoPotato();
      }
    }
    // Once leftSpeed and rightSpeed have been set, apply them to the drivetrain
    // In autonomous mode they are never changed so just continue driving with them
    Robot.drivetrain.drive(leftSpeed, rightSpeed);
  }

  protected boolean isFinished() {
    if (autonomous) {
      return isTimedOut();
    }
    else {
      return false; // Manual driving never times out
    }

  }

  protected void end() {
    Robot.drivetrain.stop();
  }

  protected void interrupted() {
    Robot.drivetrain.stop();
  }
}
