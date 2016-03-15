package src.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import src.Robot;
import src.teleop.DriveStraight;
import src.teleop.DualSetpoint;
import src.teleop.WaitCommand;


public class Autonomous extends CommandGroup{
	public Autonomous(int mode){
		System.out.println("Autonomous started");
		//DriveStraight(boolean turn,double yaw);
		//DriveStraight(double duration,boolean autonomous,boolean reverse);
		//DriveStraight(double duration,double speed, boolean autonomous,boolean reverse);
		//Duration = -1 if no duration needed.
	
		
	    switch(mode){
	    case 1: defaulted();
	    	break;
	    case 2: ramparts();
	    	break;
	    case 3: RockWall();
	    	break;
	    default: defaulted();
	   		break;
	    }
		
	}
	
	public void defaulted(){
		addSequential(new DriveStraight(0.3,0.5,true, false));
	    addSequential(new WaitCommand(0.5));
		addSequential(new DualSetpoint(Robot.Position.ShooterStowed, Robot.Position.IntakeDown));
		addSequential(new WaitCommand(1));
		addSequential(new DriveStraight(2.8,0.75,true, false));
	    addSequential(new WaitCommand(1));
	}
	
	public void ramparts(){
		addSequential(new DriveStraight(true, 15));
		addSequential(new WaitCommand(1));
		addSequential(new DriveStraight(3,true, false));
	    addSequential(new WaitCommand(1));
	    addSequential(new DriveStraight(3,true, true));
	    addSequential(new WaitCommand(1));
	    addSequential(new DriveStraight(true, 15));
	    addSequential(new WaitCommand(1));
	    addSequential(new DriveStraight(3, true, true));
	}
	
	public void RockWall(){
		addSequential(new DriveStraight(1.5,0.7,true, false));
		addSequential(new DriveStraight(2,1.0,true, false));
		addSequential(new WaitCommand(1));
		addSequential(new DriveStraight(1.5,0.7,true, true));
		addSequential(new DriveStraight(2,1.0,true, true));
	    
	}
	
}
