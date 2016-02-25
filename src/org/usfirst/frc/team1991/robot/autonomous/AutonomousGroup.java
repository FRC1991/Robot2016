package org.usfirst.frc.team1991.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousGroup extends CommandGroup{
	int defense;
	int position;
	boolean shouldShoot;
	/*Defenses:
		1. Moat
		2. Rough Terrain
		3. Rock Wall
		4. Flappy thingies
		5. Ramparts
		6. Low Bar
		7. Portcullis
		8. Door Thingy(Not during autonmous)
		9. Drawbridge(Not during autonmous)
		10. Dragon
		*/
	public AutonomousGroup(int defense, int position, boolean shouldShoot){
		this.defense = defense;
		this.position = position;
		this.shouldShoot = shouldShoot;
		autonomous();
	}
	private void autonomous(){
		//addParallel(new pitchCheck()); Shooter comes up when finished
		switch (defense) {
		case 1:
			//addSequential(new StraightDrive(time, speed));
			break;
		case 2:	//addSequential(new StraightDrive(time, speed));
					
			break;
		case 3: //addSequential(new StraightDrive(time, speed));
				//addSequential(new StraightDrive(time, speed));
			
			break;
		case 4:
			
			break;
		case 5: //addSequential(new TurnToAngle(angle));
				//addSequential(new StraightDrive(time, speed));
			
			break;
		case 6: //addSequential(new StraightDrive(time, speed));
			
			break;
		case 7: //addSequential(new StraightDrive(time, speed));
				//addSequnetial(new Lift());
				//addSequential(new StraightDrive(time, speed));
			break;
		case 8:
			
			break;
		case 9:	
			
			break;
		case 10: //addSequential(new HeatShield());
				 //addSequential(new Flamethrower());
			
			break;

		default:
			break;
		}
		
		
		
		switch (position) {
		case 1: //addSequential(new StraightDrive(time, speed));
				//addSequential(new TurnToAngle(angle));
				//addSequential(new StraightDrive(time, speed));
			
			break;
		case 2:	//addSequential(new StraightDrive(time, speed));
				//addSequential(new TurnToAngle(angle));
				//addSequential(new StraightDrive(time, speed));
					
			break;
		case 3: //addSequential(new StraightDrive(time, speed));
				//addSequential(new TurnToAngle(angle));
				//addSequential(new StraightDrive(time, speed));
				//addSequential(new TurnToAngle(angle));
			
			break;
		case 4: //addSequential(new StraightDrive(time, speed));
				//addSequential(new TurnToAngle(angle));
				//addSequential(new StraightDrive(time, speed));
				//addSequential(new TurnToAngle(angle));
			
			break;
		case 5: //addSequential(new StraightDrive(time, speed));
				//addSequential(new TurnToAngle(angle));
				//addSequential(new StraightDrive(time, speed));
			
			break;

		default:
			break;
		}
		if(shouldShoot){
			//addSequential(new RunShooter());
		}
	}
}
