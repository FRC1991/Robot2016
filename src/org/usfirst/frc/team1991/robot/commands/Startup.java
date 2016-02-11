package org.usfirst.frc.team1991.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Startup extends CommandGroup{
	public Startup(){
		addParallel(new Read("/home/lvuser/Data/testPref.txt"));
		addParallel(new ArcadeDrive());
	}
}
