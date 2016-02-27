package org.usfirst.frc.team1991.robot.shooter;
import org.usfirst.frc.team1991.robot.RobotMap;
import org.usfirst.frc.team1991.robot.Robot;
import org.usfirst.frc.team1991.robot.shooter.Shooter;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Test extends Command {

	private CANTalon LRunner;

  public Test() {
    LRunner = RobotMap.shooter_LRunner;
  }
	protected void initialize() {
    LRunner.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    LRunner.reverseSensor(false);
    LRunner.setPID(1, 0, 0);
    LRunner.changeControlMode(TalonControlMode.Speed);
	}

	protected void execute() {
    double rpm = SmartDashboard.getNumber("LRunner RPM");
    LRunner.set(rpm);
    SmartDashboard.putNumber("LRunner Output", (LRunner.getOutputVoltage() / LRunner.getBusVoltage()));
    SmartDashboard.putNumber("LRunner Speed", LRunner.getSpeed());
    SmartDashboard.putNumber("LRunner Error", LRunner.getClosedLoopError());

  	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
    LRunner.changeControlMode(TalonControlMode.PercentVbus);
		Robot.shooter.stop();
	}

	protected void interrupted() {
    LRunner.changeControlMode(TalonControlMode.PercentVbus);
		Robot.shooter.stop();
	}
}
