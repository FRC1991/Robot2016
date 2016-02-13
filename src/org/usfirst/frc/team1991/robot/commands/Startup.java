package org.usfirst.frc.team1991.robot.commands;

import org.usfirst.frc.team1991.robot.drivetrain.ArcadeDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Startup extends CommandGroup{
	public Startup(){
		addParallel(new Read("/DataFiles/TestPref.txt"));
		addParallel(new ArcadeDrive());
	}
}
