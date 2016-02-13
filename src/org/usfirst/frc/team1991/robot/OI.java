package org.usfirst.frc.team1991.robot;

import org.usfirst.frc.team1991.robot.shooter.AngleShooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick joy;
	public JoystickButton X, Y, B, A, LeftBumper;
	

    public OI() {
        joy = new Joystick(0);
        X = new JoystickButton(joy, 3);
        Y = new JoystickButton(joy, 4);
        B = new JoystickButton(joy, 2);
        A = new JoystickButton(joy, 1);
        LeftBumper = new JoystickButton(joy, 5);
//        X.whenPressed(new TankDrive());
//        Y.whenPressed(new ArcadeDrive());
//        B.toggleWhenPressed(new ReverseDrive());
//       // A.whenPressed(new FireShooter());
//        A.whenReleased(new StraightDrive(0.4, 3));
        X.whenPressed(new AngleShooter(1.5));
        Y.whenPressed(new AngleShooter(3));
        A.whenPressed(new AngleShooter(1));
        B.whenPressed(new AngleShooter(3.5));
    }
}

