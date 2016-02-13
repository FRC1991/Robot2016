
package org.usfirst.frc.team1991.robot.drivetrain;

import java.util.List;

import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.commands.Startup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	List<CANTalon> leftSide = RobotMap.leftSide;
	List<CANTalon> rightSide = RobotMap.rightSide;
	double multiplier = 0.5;
	double leftOff = 0;
	double rightOff = 0;
    public static boolean reverse = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new Startup());
    }
    
    private void driveSide(List<CANTalon> side, double speed, String sides) {
    	if(sides.equals("left")){
    		speed += leftOff;
    	}else{
    		speed += rightOff;
    	}
    	for (CANTalon talon: side) {
    		talon.set(speed * 0.5);
    	}
    }
    
   public void drive(double left, double right) {
	   if(reverse){
		   driveSide(leftSide, right * -1, "left");
		   driveSide(rightSide, left, "right");
	   }else{
		   driveSide(leftSide, left, "left");
		   driveSide(rightSide, right * -1, "right");
	   }
	   
	    // Due to technical reasons, the right side is reversed. Multiply by -1 to address this.
   }
    
   	public void setVars(double[] vars){
   		multiplier = vars[0];
   		leftOff = vars[1];
   		rightOff = vars[2];
   		System.out.println(multiplier + " " + leftOff);
   	}

	public static boolean isReverse() {
		return reverse;
	}
	
	public static void setReverse(boolean reverse) {
		Drivetrain.reverse = reverse;
	}
   
   
}

