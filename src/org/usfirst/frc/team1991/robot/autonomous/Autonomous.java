package org.usfirst.frc.team1991.robot.autonomous;

import org.usfirst.frc.team1991.robot.teleop.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Autonomous extends CommandGroup{
	public Autonomous(){
		System.out.println("Autonomous started");
		//DriveStraight(boolean turn,double yaw);
		//DriveStraight(double duration,boolean autonomous,boolean reverse);
		//Duration = -1 if no duration needed.
		addSequential(new DriveStraight(-1,true, true));
		addSequential(new DriveStraight(1, true, true));
		addSequential(new WaitCommand(1));
	    addSequential(new DriveStraight(true, 180));
	    addSequential(new WaitCommand(1));
	    addSequential(new DriveStraight(-1,true, false));
	    addSequential(new DriveStraight(1, true, false));
		addSequential(new WaitCommand(1));
	    addSequential(new DriveStraight(true, 180));
	}
	
}
