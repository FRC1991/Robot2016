package org.usfirst.frc.team1991.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.PIDController;

public class PIDControllerConfiguration {
	public double p, i, d, tolerance, min, max;
	public boolean continuous;

	// Sets config values based on parameters
	public PIDControllerConfiguration(double p, double i, double d, double tolerance, double min, double max, boolean continuous) {
		this.p = p;
		this.i = i;
		this.d = d;
		this.tolerance = tolerance;
		this.min = min;
		this.max = max;
		this.continuous = continuous;
	}

	//Sets config values based on parameter
	public PIDControllerConfiguration(String prefsKey) {
		if(!keys.contains(prefsKey)) {
			keys.add(prefsKey);
		}
		this.p = RobotMap.pref.get(prefsKey + "_P");
		this.i = RobotMap.pref.get(prefsKey + "_I");
		this.d = RobotMap.pref.get(prefsKey + "_D");
		this.tolerance = RobotMap.pref.get(prefsKey + "_Tolerance");
		this.min = RobotMap.pref.get(prefsKey + "_Min");
		this.max = RobotMap.pref.get(prefsKey + "_Max");
		this.continuous = (RobotMap.pref.get(prefsKey + "_Continuous") == 1.0) ? true : false;
	}

	//Configures PIDController
	public void configurePIDController(PIDController controller) {
		controller.setPID(this.p, this.i, this.d);
		controller.setAbsoluteTolerance(this.tolerance);
		controller.setContinuous(this.continuous);
		controller.setInputRange(this.min, this.max);
		controller.setOutputRange(-1, 1);
	}

}
