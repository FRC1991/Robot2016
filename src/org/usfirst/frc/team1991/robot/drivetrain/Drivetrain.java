
package org.usfirst.frc.team1991.robot.drivetrain;

import java.util.List;

import org.usfirst.frc.team1991.robot.*;
import org.usfirst.frc.team1991.robot.drivetrain.Drive;
import org.usfirst.frc.team1991.robot.drivetrain.Drive.ManualDriveModes;



import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem responsible for controlling the motors that run the drivetrain.
 */
public class Drivetrain extends Subsystem {

  	List<CANTalon> leftSide = RobotMap.leftSide;
  	List<CANTalon> rightSide = RobotMap.rightSide;
    public boolean reverse = false;


    public void initDefaultCommand() {
    	setDefaultCommand(new Drive(ManualDriveModes.ARCADE, Robot.oi.joy));
    }

    private void driveSide(List<CANTalon> side, double speed) {
      for (CANTalon talon: side) {
        talon.set(speed);
    	}
    }

   public void drive(double leftSpeed, double rightSpeed) {
     // Perform any final operations on the two speeds
     leftSpeed += RobotMap.DRIVETRAIN_LEFT_SPEED_OFFSET;
     rightSpeed += RobotMap.DRIVETRAIN_RIGHT_SPEED_OFFSET;
     leftSpeed *= RobotMap.DRIVETRAIN_MAX_SPEED_MULTIPLIER;
     rightSpeed *= RobotMap.DRIVETRAIN_MAX_SPEED_MULTIPLIER;
     rightSpeed *= -1; // Right side is always inverted
     if (reverse) {
       driveSide(leftSide, rightSpeed);
       driveSide(rightSide, leftSpeed);
     }
     else {
       driveSide(leftSide, leftSpeed);
       driveSide(rightSide, rightSpeed);
     }
   }

   public void stop() {
     driveSide(leftSide, 0);
     driveSide(rightSide, 0);
   }
}
