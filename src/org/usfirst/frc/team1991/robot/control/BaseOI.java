package org.usfirst.frc.team1991.robot.control;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class BaseOI {
  public static Driver driver;
  public static Aux aux;

  public BaseOI() {
    driver = new Driver(0);
    aux = new Aux(1);
  }
}
