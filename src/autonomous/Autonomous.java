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
	
	//Time/Speed from waterbury
	private final double driveTime = 2;
	private final double driveSpeed = 0.7;
	
	public Autonomous() {
		//Moves Up then Stows Intake/Shooter
		addSequential(new DriveTime(0.4, 0.5));//Move up to avoid foul
		addSequential(new MoveSystemsToPositions(Robot.Position.IntakeDown, Robot.Position.ShooterStowed));
		//addSequential(new WaitCommand(0.5));
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
		addSequential(new WaitCommand(.5));
		positionStrategy();
    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterAutoAim));
    	addSequential(new DistanceReckon());
    	addSequential(new WaitCommand(1));
    	addSequential(new TurnToAlignWithTarget());
	}
	
	private void moveUpShoot(){
		moveUpNoShoot();
		//addSequential(new WaitCommand(0.5));
		shoot();
	}
	
	private void shoot(){
    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterHighGoal));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new Shoot());
	}
	
	private void positionStrategy(){
		switch(position){
		//Low Bar
		case 1: addSequential(new TurnToYaw(-30));
			break;
		//A Bit Left
		case 2: addSequential(new TurnToYaw(-15));
			break;
		//Middle
		case 3: System.out.println("No need to turn!");
			break;
		//Middle
		case 4: System.out.println("No need to turn!");
			break;
		//A Bit Right
		case 5: addSequential(new TurnToYaw(15));
			break;
		}
	}
}
