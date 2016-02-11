
package org.usfirst.frc.team1991.robot.subsystems;

import java.util.List;

import org.usfirst.frc.team1991.robot.Preferences;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.commands.Startup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	List<CANTalon> leftSide = RobotMap.leftDriveSide;
	List<CANTalon> rightSide = RobotMap.rightDriveSide;
	Preferences pref = Robot.pref;
	double multiplier = pref.getSpeed_multiplier();
	double rightOffset = pref.getSpeed_offset_right();
	double leftOffset = pref.getSpeed_offset_left();
	double rotateSpeed = pref.getSpeed_rotate();
	Joystick joy = Robot.oi.joy;
    public static boolean reverse = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new Startup());
    }
    //Drives 1 of the sides in the tank drive based on side parameter
    private void driveSide(List<CANTalon> side, double speed, String sides) {
    	if(sides.equals("left")){
    		speed += leftOffset;
    	}else if(sides.equals("right")){
    		speed += rightOffset;
    	}
    	for (CANTalon talon: side) {
    		talon.set(speed * multiplier);
    	}
    }
    
   //Gets left and right side speeds and outputs to driveSide() 
   public void drive(double left, double right) {
	   if(joy.getRawAxis(2) > 0|| joy.getRawAxis(3) > 0){
		   rotate();
	   }else if(joy.getRawButton(5)){//Makes robot move straight forwards
		   left = -1;
  			right = -1;
	   }else if(joy.getRawButton(6)){//Makes robot move straight backwards
		   left = 1;
   			right = 1;
	   }else{
		   if(reverse){
			   driveSide(leftSide, right * -1, "left");
			   driveSide(rightSide, left, "right");
		   }else{
			   driveSide(leftSide, left, "left");
			   driveSide(rightSide, right * -1, "right");
		   }
	   }
	   
	    // Due to technical reasons, the right side is reversed. Multiply by -1 to address this.
   }
// Turn left or right with a variable speed if left/right trigger is held
   public void rotate(){
	   double left;
	   double right;
	   if(reverse){
		   
		   if(joy.getRawAxis(2) > 0){
	    		right = -joy.getRawAxis(2);
	    		left = -right;
	    	}
	    	if(joy.getRawAxis(3) > 0){
	    		left = -joy.getRawAxis(3);
	    		right = -left;
	    	}
		   
	   }else{
		
	    	if(joy.getRawAxis(2) > 0){
	    		left = -joy.getRawAxis(2);
	    		right = -left;
	    	}
	    	if(joy.getRawAxis(3) > 0){
	    		right = -joy.getRawAxis(3);
	    		left= - right;
	    	}
		   
	   }
   }
  

	public static boolean isReverse() {
		return reverse;
	}
	
	public static void setReverse(boolean reverse) {
		Drivetrain.reverse = reverse;
	}
   
   
}

