package org.usfirst.frc.team1991.robot.drivetrain;

import java.util.HashMap;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.control.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ManualDrive extends Command {
	private Driver driver;

	public ManualDrive() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
		driver = BaseOI.driver;
	}

	protected void execute() {
		// 0 - left, 1 - right
		double[] speeds = driver.getSpeeds();
    // Handle rotation
		if (driver.triggersPressed()) {
			double[] triggers = driver.getTriggerValues();
			if (triggers[0] > 0) {
				speeds[0] = -triggers[0];
				speeds[1] = -speeds[0];
			}
			else {
				speeds[1] = -triggers[1];
				speeds[0] = -speeds[1];
			}
			speeds[0] *= RobotMap.prefs.get("Drivetrain_Speed_Rotation");
			speeds[1] *= RobotMap.prefs.get("Drivetrain_Speed_Rotation");
		}
    Robot.drivetrain.drive(speeds[0], speeds[1]);
	}

protected boolean isFinished() {
	return false;

}

protected void end() {
	Robot.drivetrain.stop();
}

protected void interrupted() {
	Robot.drivetrain.stop();
}
}
