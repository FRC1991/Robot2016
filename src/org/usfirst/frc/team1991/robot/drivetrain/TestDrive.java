package org.usfirst.frc.team1991.robot.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestDrive extends CommandGroup {

    public TestDrive() {
    	// TurnToAngle(double angle);
    	// DriveStraight (double speed, double distance);
    	// WaitForTargetDetection();
    	// AlignWithTarget();
    	// Drive(double leftSpeed, double rightSpeed, double duration);
    	//		double distance?
    	// IntakeAngle(Shots shot);
    	// FireShooter();
        addSequential(new Drive(0.5, 0.5, 3));
        addSequential(new Drive(1, 0, 1.3));
        addSequential(new Drive(0.6, 0.6, 1));
    }
}
