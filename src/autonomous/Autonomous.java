package src.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import src.Robot;
import src.XCommand;
import src.teleop.DriveStraight;
import src.teleop.SetDriveBackwards;
import src.teleop.Shoot;

public class Autonomous extends CommandGroup{
	private int mode, position;
	private final double driveTime = 4.2;
	private final double driveSpeed = 0.2;
	
	public Autonomous() {
		addSequential(new MoveSystemsToPositions(Robot.Position.IntakeDown, Robot.Position.ShooterStowed));
		addSequential(new WaitCommand(1));
		mode = (int)SmartDashboard.getNumber("Autonomous Mode", 0);
		position = (int)SmartDashboard.getNumber("Autonomous Position", 0);
		System.out.println("Autonomous started in mode " + mode + ", position " + position);
		switch(mode) {
		case 0:
	    	addSequential(new DriveTime(driveTime, driveSpeed));
	    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterAutoAim));
			break;
	    case 1: 
	    	addSequential(new DriveTime(driveTime, driveSpeed));
	    	break;
	    case 2:
//	    	addSequential(new DriveTime(driveTime, driveSpeed));
//	    	addSequential(new SetDriveBackwards(true));
//	    	addSequential(new WaitCommand(0.5));
//	    	addSequential(new DriveTime(driveTime, driveSpeed));
//	    	addSequential(new SetDriveBackwards(false));
	    	// Drive and auto aim
	    	addSequential(new DriveTime(driveTime, driveSpeed));
	    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterAutoAim));
	    	addSequential(new WaitCommand(2));
	    	addSequential(new TurnToAlignWithTarget());
	    	break;
	    case 3:
	    	addSequential(new DriveTime(driveTime, driveSpeed));
	    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterAutoAim));
	    	addSequential(new WaitCommand(2));
	    	addSequential(new TurnToAlignWithTarget());
	    	addSequential(new WaitCommand(0.3));
	    	// Check if shot should be taken
	    	addSequential(new XCommand() {
	    		private double farthestDistance = 11.5;
	    		private double lowestDistance = 7;
				@Override
				protected void execute() {
				}
				
				protected boolean isFinished() {
					double distance = SmartDashboard.getNumber("Distance", 100);
					return (distance > lowestDistance && distance < farthestDistance);
					
				}
	    	});
	    	addSequential(new MoveShooterToPosition(Robot.Position.ShooterFeed));
	    	addSequential(new WaitCommand(1));
	    	addSequential(new Shoot());
	    	break;
	    default: 
	   		break;
	    }
	}
}
