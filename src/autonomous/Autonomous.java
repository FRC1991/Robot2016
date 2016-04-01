package src.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;
import src.teleop.DriveStraight;
import src.teleop.SetDriveBackwards;
import src.teleop.Shoot;

public class Autonomous extends CommandGroup {
	private int mode, position;
	private final double driveTime = 2.5;
	private final double driveSpeed = 0.3;
	
	public Autonomous() {
		addSequential(new MoveSystemsToPositions(Robot.Position.IntakeDown, Robot.Position.ShooterStowed));
		mode = (int)SmartDashboard.getNumber("Autonomous Mode", 0);
		position = (int)SmartDashboard.getNumber("Autonomous Position", 0);
		
		System.out.println("Autonomous started in mode " + mode + ", position " + position);
		
		switch(mode) {
		case 0: System.out.println("Not doing anything.");
			break;
	    case 1: moveUp();
	    	break;
	    case 2: moveUpNoShoot();
	    	break;
	    case 3: moveUpShoot();
	    	break;
	    default: System.out.println("Not doing anything. ");
	   		break;
	    }
	}
	
	private void moveUp(){
		addSequential(new DriveTime(driveTime, driveSpeed));
	}
	
	private void moveUpNoShoot(){
		moveUp();
		//positionStrategy();
    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterAutoAim));
    	addSequential(new WaitCommand(2));
    	addSequential(new TurnToAlignWithTarget());
    	addSequential(new WaitCommand(1));
    	addSequential(new DistanceReckon());
	}
	
	private void moveUpShoot(){
		moveUpNoShoot();
		addSequential(new WaitCommand(0.5));
		shoot();
	}
	
	private void shoot(){
    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterFeed));
    	addSequential(new WaitCommand(1));
    	addSequential(new Shoot());
	}
	
	private void positionStrategy(){
		switch(position){
		//Low Bar
		case 1: addSequential(new TurnToYaw(15));
			break;
		//A Bit Left
		case 2: addSequential(new TurnToYaw(10));
			break;
		//Middle
		case 3: System.out.println("No need to turn!");
			break;
		//Middle
		case 4: System.out.println("No need to turn!");
			break;
		//A Bit Right
		case 5: addSequential(new TurnToYaw(-10));
			break;
		}
	}
}
