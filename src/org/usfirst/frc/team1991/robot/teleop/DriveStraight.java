package org.usfirst.frc.team1991.robot.teleop;

import java.util.HashMap;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;


public class DriveStraight extends Command {
  private XboxController driver;
  private double initialYaw;

  public DriveStraight() {
    requires(Robot.drivetrain);
  }

  protected void initialize() {
    driver = Robot.driver;
    initialYaw = Robot.drivetrain.getPosition();
    Robot.drivetrain.enable();
  }

  protected void execute() {
    double speed = driver.getRJoystickY();
    Robot.drivetrain.setYawAndSpeed(initialYaw, speed);
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
