package src.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.teleop.DriveStraight;
import src.teleop.Shoot;

public class Autonomous extends CommandGroup{
	int mode = 1;
	int position = 1;
	public Autonomous(){
		System.out.println("Autonomous started");
		//DriveStraight(boolean turn,double yaw);
		//DriveStraight(double duration,boolean autonomous,boolean reverse);
		//DriveStraight(double duration,double speed, boolean autonomous,boolean reverse);
		//Duration = -1 if no duration needed.

		

		mode = (int)SmartDashboard.getNumber("Autonomous Mode");
		position = (int)SmartDashboard.getNumber("Autonomous Position");
	    switch(mode){
	    case 1: test1();
	    	break;
	    case 2: test2();
	    	break;
	    case 3: test3();
	    	break;
	    default: 
	   		break;
	    }

	}

	public void defaulted(){
		addSequential(new DriveStraight(0.3,0.5,true, false));
	    addSequential(new WaitCommand(0.5));
		addSequential(new MoveSystemsToPositions(Robot.Position.IntakeDown, Robot.Position.ShooterStowed));
		addSequential(new WaitCommand(1));
		addSequential(new DriveStraight(2.8,0.75,true, false));
	    addSequential(new WaitCommand(1));
	}

	public void test1(){
		//Do Nothing
		addSequential(new MoveSystemsToPositions(Robot.Position.IntakeDown, Robot.Position.ShooterFeed));
		addSequential(new WaitCommand(1));
		//5vc0ltuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	//ddSequential(new Shoot());
	}
	
	public void test2(){
		//Drive Straight
		addSequential(new DriveStraight(1,0.3,true, false));
	    addSequential(new WaitCommand(0.5));
	    addSequential(new MoveSystemsToPositions(Robot.Position.IntakeDown, Robot.Position.ShooterFeed));
	}
	
	public void test3(){
		//Drive Straigh and back
		addSequential(new DriveStraight(1,0.3,true, false));
	    addSequential(new WaitCommand(1));
	    addSequential(new DriveStraight(1,0.3,true, true));
	}

}
