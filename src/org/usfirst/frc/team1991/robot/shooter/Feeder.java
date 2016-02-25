package org.usfirst.frc.team1991.robot.shooter;

import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Run the feeder until a ball is detected.
 */
public class Feeder extends Command {

	DigitalInput intakeSensor = Robot.shooter.intakeSensor;
	
	public Feeder() {
		requires(Robot.shooter);
	}
    protected void initialize() {
    }

    protected void execute() {
    	if (!Robot.shooter.ballPresent) {
        	Robot.shooter.feed(0.35);
    	}
    	Robot.shooter.ballPresent = !intakeSensor.get(); // Sensor returns 1 when nothing is detected
    }

    protected boolean isFinished() {
        return Robot.shooter.ballPresent;
    }

    protected void end() {
    	Robot.shooter.stop();
    	
    }

    protected void interrupted() {
    	Robot.shooter.stop();
    }
}
