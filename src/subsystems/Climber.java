package src.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import src.teleop.MoveClimberManually;

public class Climber extends Subsystem {

	private CANTalon left, right;
	
	public Climber() {
		left = new CANTalon(11);
		right = new CANTalon(12);
	}
	
	public void periodic() {
		return;
	}
	
	public void disable() {
		left.set(0);
		right.set(0);
	}
	
	public void move(double speed) {
		left.set(speed);
		right.set(speed);
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
