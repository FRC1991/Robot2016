
package org.usfirst.frc.team1991.robot;

import org.usfirst.frc.team1991.robot.drivetrain.Drivetrain;
import org.usfirst.frc.team1991.robot.drivetrain.TestDrive;
import org.usfirst.frc.team1991.robot.shooter.Shooter;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static Shooter shooter;
	public static OI oi;
	public static Camera cam;

    Command autonomousCommand;
    SendableChooser chooser;

    public void robotInit() {
    	RobotMap.init();
    	drivetrain = new Drivetrain();
    	shooter = new Shooter();
			oi = new OI();
			cam = new Camera();
      autonomousCommand = new TestDrive();
    }

    public void disabledInit() {
			Robot.shooter.stop();
			Robot.drivetrain.stop();
    }

		public void disabledPeriodic() {
				Scheduler.getInstance().run();
		}

    public void autonomousInit() {
        //autonomousCommand = (Command) chooser.getSelected();
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ArcadeDrive();
			break;
		} */

    	// schedule the autonomous command (example)
        autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    		RobotMap.navX.reset();
				cam.startCamera("cam1");
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
    		cam.refreshFrame();
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}
