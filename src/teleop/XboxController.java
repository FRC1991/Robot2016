package src.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController {
	private Joystick controller;
	public JoystickButton X, Y, A, B, LBumper, RBumper, Start, Select, LJoystick, RJoystick;

	public XboxController(int controllerPort) {
		controller = new Joystick(controllerPort);
		X = new JoystickButton(controller, 3);
		Y = new JoystickButton(controller, 4);
		B = new JoystickButton(controller, 2);
		A = new JoystickButton(controller, 1);
		LBumper = new JoystickButton(controller, 5);
		RBumper = new JoystickButton(controller, 6);
		LJoystick = new JoystickButton(controller, 9);
		RJoystick = new JoystickButton(controller, 10);
		Start = new JoystickButton(controller, 8);
		Select = new JoystickButton(controller, 7);
	}

	public double axis(int axis) {
		double value = controller.getRawAxis(axis);
		// On an Xbox controller, full up on the Y axis equals -1, so multiply by -1 to get 1
		if (( axis == 1) || ( axis == 5) ) {
			value *= -1;
		}
		return value;
	}

	public double getLJoystickX() {
		return axis(0);
	}

	public double getLJoystickY() {
		return axis(1);
	}

	public double getRJoystickX() {
    System.out.println("RJoystickX not mapped.");
    return 0;
  }

	public double getRJoystickY() {
		return axis(5);
	}

  public double getLTrigger() {
		return axis(2);
	}

	public double getRTrigger() {
		return axis(3);
	}

	public boolean areTriggersPressed() {
		return (getLTrigger() > 0 || getRTrigger() > 0);
	}

}
