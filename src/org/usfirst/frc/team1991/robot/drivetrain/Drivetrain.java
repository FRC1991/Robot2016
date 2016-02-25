
package org.usfirst.frc.team1991.robot.drivetrain;

import java.util.HashMap;
import java.util.List;

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

	Talon leftSide = RobotMap.leftSide;
	Talon rightSide = RobotMap.rightSide;
	
    public boolean reverse = false;


    public void initDefaultCommand() {
    	setDefaultCommand(new Drive(ManualDriveModes.ARCADE, Robot.oi.joy));
    }
    
    private void driveSide(Talon side, double speed) {
    	try{
    		side.set(speed);
    	}catch(Exception e){
    		
    	}
      }

   public void drive(double leftSpeed, double rightSpeed) {
     // Perform any final operations on the two speeds
     leftSpeed += RobotMap.pref.get("Drivetrain_Offset_Left");
     rightSpeed += RobotMap.pref.get("Drivetrain_Offset_Right");
     leftSpeed *= RobotMap.pref.get("Drivetrain_Speed_Multiplier");
     rightSpeed *= RobotMap.pref.get("Drivetrain_Speed_Multiplier");
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
