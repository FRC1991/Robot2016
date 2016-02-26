
package org.usfirst.frc.team1991.robot.drivetrain;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import org.usfirst.frc.team1991.robot.drivetrain.Drive.ManualDriveModes;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem responsible for controlling the motors that run the drivetrain.
 */
public class Drivetrain extends Subsystem {

	ArrayList<CANTalon> leftSide = RobotMap.drivetrain_LSide;
	ArrayList<CANTalon> rightSide = RobotMap.drivetrain_RSide;

    public boolean reverse = false;


    public void initDefaultCommand() {
    	setDefaultCommand(new Drive(ManualDriveModes.ARCADE, Robot.oi.joy));
    }

    private void driveSide(ArrayList<CANTalon> side, double speed) {
    		for (CANTalon motor : side) {
					motor.set(speed);
				}
			}

   public void drive(double leftSpeed, double rightSpeed) {
     // Perform any final operations on the two speeds
     leftSpeed += RobotMap.prefs.get("Drivetrain_Offset_Left");
     rightSpeed += RobotMap.prefs.get("Drivetrain_Offset_Right");
     leftSpeed *= RobotMap.prefs.get("Drivetrain_Speed_Multiplier");
     rightSpeed *= RobotMap.prefs.get("Drivetrain_Speed_Multiplier");
     if (reverse) {
       driveSide(leftSide, rightSpeed);
       driveSide(rightSide, leftSpeed);
     }
     else {
       driveSide(leftSide, rightSpeed);
       driveSide(rightSide, leftSpeed);
     }
   }

   public void stop() {
     driveSide(leftSide, 0);
     driveSide(rightSide, 0);
   }
}
