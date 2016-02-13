
package org.usfirst.frc.team1991.robot.drivetrain;

import java.util.List;

import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.commands.Startup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	List<CANTalon> leftSide = RobotMap.leftSide;
	List<CANTalon> rightSide = RobotMap.rightSide;
	double multiplier;
	double leftOff;
	double rightOff;
	double rot;
    public static boolean reverse = false;
    Joystick gamepad = new Joystick(0);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new Startup());
    }
    
    private void driveSide(List<CANTalon> side, double speed, String sides) {
    	if(gamepad.getRawAxis(2) > 0 || gamepad.getRawAxis(3) > 0){
    		speed *= rot;
    	}else{
    		speed *= multiplier;
    	}
    	
    	if(speed > 0.1){
    		if(sides.equals("left")){
        		speed += leftOff;
        	}else{
        		speed += rightOff;
        	}
		}
    	
    	
    	for (CANTalon talon: side) {
    		
    		talon.set(speed);
    	}
    	
    }
    
   public void drive(double left, double right) {
	   System.out.println(multiplier + " " + right);
	   if(gamepad.getRawAxis(2) > 0 || gamepad.getRawAxis(3) > 0){
		   rotate();
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
    
   	public void setVars(double[] vars){
   		multiplier = vars[0];
   		leftOff = vars[1];
   		rightOff = vars[2];
   		rot = vars[3];
   		System.out.println(multiplier + " " + leftOff);
   	}
   	
   	public void setValues(Double[] speeds){
   	
   		try{
   			multiplier = speeds[0];
   	   		leftOff = speeds[1];
   	   		rightOff = speeds[2];
   	   		rot = speeds[3];
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		
   	}
   	
   	public void rotate(){
   		double leftSpeed = 0;
   		double rightSpeed = 0;
   		if(gamepad.getRawAxis(2) > 0){
    		leftSpeed = -gamepad.getRawAxis(2);
    		rightSpeed = -leftSpeed;
    	}
    	if(gamepad.getRawAxis(3) > 0){
    		rightSpeed = -gamepad.getRawAxis(3);
    		leftSpeed = -rightSpeed;
    	}
    	  if(reverse){
			   driveSide(leftSide, rightSpeed * -1, "left");
			   driveSide(rightSide, leftSpeed, "right");
		   }else{
			   driveSide(leftSide, leftSpeed, "left");
			   driveSide(rightSide, rightSpeed * -1, "right");
		   }
   	}

	public static boolean isReverse() {
		return reverse;
	}
	
	public static void setReverse(boolean reverse) {
		Drivetrain.reverse = reverse;
	}
   
   
}

