package src.teleop;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command{
	double time;
	public WaitCommand(double time){
		this.time = time;
	}

	@Override
	protected void initialize() {
		setTimeout(time);
		
	}

	@Override
	protected void execute() {
		
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
