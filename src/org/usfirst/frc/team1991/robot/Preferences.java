package org.usfirst.frc.team1991.robot;

import java.util.HashMap;
import java.util.Map;


//Gets preferences from the pref file on the robot
public class Preferences {
	
	
	
	double speed_offset_right = 0.0;
	double speed_offset_left = 0.0;
	double speed_multiplier = 0.5;
	double speed_rotate = 0.5;
	
	Map<String,String> prefs = new HashMap<String,String>();
	
	public Preferences(){
		
	}
	public void setPrefs(Map prefs){
		try{
		speed_offset_right = Double.parseDouble((String) prefs.get("speedR"));
		speed_offset_left = Double.parseDouble((String) prefs.get("speedL"));
		speed_multiplier = Double.parseDouble((String) prefs.get("speedMultiplier"));
		speed_rotate = Double.parseDouble((String) prefs.get("speedRotate"));
		}catch(Exception e){
			e.printStackTrace();
		}
		//double[] vars = {speed_multiplier, speed_offset_left, speed_offset_right};
		double[] vars = {0.5, 0.0, 0.0};
		Robot.drivetrain.setVars(vars);
	}
	
	//Getters and Setters 
	public double getSpeed_offset_right() {
		return speed_offset_right;
	}
	public void setSpeed_offset_right(double speed_offset_right) {
		this.speed_offset_right = speed_offset_right;
	}
	public double getSpeed_offset_left() {
		return speed_offset_left;
	}
	public void setSpeed_offset_left(double speed_offset_left) {
		this.speed_offset_left = speed_offset_left;
	}
	public double getSpeed_multiplier() {
		return speed_multiplier;
	}
	public void setSpeed_multiplier(double speed_multiplier) {
		this.speed_multiplier = speed_multiplier;
	}
	public double getSpeed_rotate() {
		return speed_rotate;
	}
	public void setSpeed_rotate(double speed_rotate) {
		this.speed_rotate = speed_rotate;
	}
	
}
