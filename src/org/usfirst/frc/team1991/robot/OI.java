package org.usfirst.frc.team1991.robot;

import org.usfirst.frc.team1991.robot.drivetrain.TurnToAngle;
import org.usfirst.frc.team1991.robot.intake.IntakeFeeder;
import org.usfirst.frc.team1991.robot.shooter.Feeder;
import org.usfirst.frc.team1991.robot.shooter.FireShooter;
import org.usfirst.frc.team1991.robot.shooter.PrepareForShotPosition;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick joy;
	public JoystickButton X, Y, B, A, LeftBumper, RightBumper;


    public OI() {
        joy = new Joystick(0);
        X = new JoystickButton(joy, 3);
        Y = new JoystickButton(joy, 4);
        B = new JoystickButton(joy, 2);
        A = new JoystickButton(joy, 1);
        LeftBumper = new JoystickButton(joy, 5);
        RightBumper = new JoystickButton(joy, 6);
        LeftBumper.whenPressed(new Feeder());
        LeftBumper.whenPressed(new IntakeFeeder());
        RightBumper.whenPressed(new FireShooter());
        Y.whenPressed(new PrepareForShotPosition(ShotPositions.BARF));
        A.whenPressed(new PrepareForShotPosition(ShotPositions.STOWED));
        X.whenPressed(new PrepareForShotPosition(ShotPositions.CLOSE_SHOT));
        B.whenPressed(new PrepareForShotPosition(ShotPositions.FAR_SHOT));
       // X.whenPressed(new TurnToAngle(SmartDashboard.getNumber("Target Yaw")));

    }
}
