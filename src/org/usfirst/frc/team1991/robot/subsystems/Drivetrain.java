
package org.usfirst.frc.team1991.robot.subsystems;

import java.util.List;

import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	List<CANTalon> leftSide = RobotMap.leftDriveSide;
	List<CANTalon> rightSide = RobotMap.rightDriveSide;
	double multiplier = 0.5;
    public static boolean reverse = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
    
    private void driveSide(List<CANTalon> side, double speed) {
    	for (CANTalon talon: side) {
    		talon.set(speed * multiplier);
    	}
    }
    
   public void drive(double left, double right) {
	   if(reverse){
		   driveSide(leftSide, right * -1);
		   driveSide(rightSide, left);
	   }else{
		   driveSide(leftSide, left);
		   driveSide(rightSide, right * -1);
	   }
	   
	    // Due to technical reasons, the right side is reversed. Multiply by -1 to address this.
   }

	public static boolean isReverse() {
		return reverse;
	}
	
	public static void setReverse(boolean reverse) {
		Drivetrain.reverse = reverse;
	}
   
   
}

