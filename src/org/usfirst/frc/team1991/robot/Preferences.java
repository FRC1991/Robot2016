package org.usfirst.frc.team1991.robot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Preferences {
	
	
	public HashMap<String, Double> prefs;
	public HashMap<String, Double> TempPrefs;
	
	File file;
	FileReader fr;
	BufferedReader br;
	
	@SuppressWarnings("unchecked")
	public Preferences(String dir){
		
		prefs = new HashMap<String,Double>();
		setPrefs();
		TempPrefs = (HashMap<String, Double>) prefs.clone();
		try{
    		file = new File(dir);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	//Gets values from readPrefs() and set prefs
	public void setValues(){
		double value;
		HashMap<String, String> newPrefs = readPrefs();
		for(String s: TempPrefs.keySet()){
			String key = s;
			if(newPrefs.containsKey(key)){
				value = prefs.get(key);
				try{
					value = Double.parseDouble(newPrefs.get(key));
				}catch(Exception e){
					e.printStackTrace();
				}
				prefs.remove(key);
				prefs.put(key, value);
			}
		}
		
	}
	//Reads user Preferences from a text file written in AakashLang
	public HashMap<String, String> readPrefs(){
		HashMap<String, String> values= new HashMap<String, String>();
		try{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String temp;
			String[] vals;
			String value;
			String key;
			while((temp = br.readLine()) != null){
				if(temp.substring(0, 1).equals("#")){
					vals = temp.split(" ");
					key = vals[0].substring(1);
					value = vals[2];
					if(key.substring(key.length() - 3).equals("PID")){
						String[] PID = value.split(",");
						key = key.substring(0, key.length() - 3);
						try{
							values.put(key + "P", PID[0]);
							values.put(key + "I", PID[1]);
							values.put(key + "D", PID[2]);
							values.put(key + "Tolerance", PID[3]);
							values.put(key + "Min", PID[4]);
							values.put(key + "Max", PID[5]);
						}catch(Exception e){
							e.printStackTrace();
						}
					}else{
						
						values.put(key, value);
					}
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return values;
	}

	//Gets a value from the HashMap prefs based on key
	public double get(String key) {
		if(prefs.containsKey(key)){
			return prefs.get(key);
		}else{
			return 0.0;
		}
	}

	//Sets default preference values
	public void setPrefs() {
		prefs.put("Drivetrain_Speed_Multiplier", 0.5);
		prefs.put("Drivetrain_Speed_Rotation", 0.5);
		prefs.put("Drivetrain_Offset_Left", 0.0);
		prefs.put("Drivetrain_Offset_Right", 0.0);
		prefs.put("Arcade_Turn_Sensitivity", 0.7);
		prefs.put("Shooter_Up_Max", 2.560);
		prefs.put("Shooter_Up_Min", 1.425);
		prefs.put("Shooter_Up_Tolerance", .023);
		prefs.put("Shooter_Up_P", 4.0);
		prefs.put("Shooter_Up_I", 0.0);
		prefs.put("Shooter_Up_D", 0.0);
		prefs.put("Shooter_Up_Continuous", 0.0);
		prefs.put("Shooter_Down_Max", 2.560);
		prefs.put("Shooter_Down_Min", 1.425);
		prefs.put("Shooter_Down_Tolerance", .023);
		prefs.put("Shooter_Down_P", 1.0);
		prefs.put("Shooter_Down_I", 0.0);
		prefs.put("Shooter_Down_D", 0.0);
		prefs.put("Shooter_Down_Continuous", 0.0);
		prefs.put("DriveTrain_Turn_P", 0.04);
		prefs.put("DriveTrain_Turn_I", 0.006);
		prefs.put("DriveTrain_Turn_D", 0.115);
		prefs.put("DriveTrain_Turn_Max", 180.0);
		prefs.put("DriveTrain_Turn_Min", -180.0);
		prefs.put("DriveTrain_Turn_Tolerance", 0.3);
		prefs.put("DriveTrain_Turn_Continuous", 0.0);
		prefs.put("Intake_Up_P", 0.04);
		prefs.put("Intake_Up_I", 0.006);
		prefs.put("Intake_Up_D", 0.115);
		prefs.put("Intake_Up_Max", 180.0);
		prefs.put("Intake_Up_Min", -180.0);
		prefs.put("Intake_Up_Tolerance", 0.3);
		prefs.put("Intake_Up_Continuous", 0.0);
		prefs.put("Intake_Down_P", 0.04);
		prefs.put("Intake_Down_I", 0.006);
		prefs.put("Intake_Down_D", 0.115);
		prefs.put("Intake_Down_Max", 180.0);
		prefs.put("Intake_Down_Min", -180.0);
		prefs.put("Intake_Down_Tolerance", 0.3);
		prefs.put("Intake_Down_Continuous", 0.0);
		prefs.put("Intake_Up1_P", 0.04);
		prefs.put("Intake_Up1_I", 0.006);
		prefs.put("Intake_Up1_D", 0.115);
		prefs.put("Intake_Up1_Max", 180.0);
		prefs.put("Intake_Up1_Min", -180.0);
		prefs.put("Intake_Up1_Tolerance", 0.3);
		prefs.put("Intake_Up1_Continuous", 0.0);
		prefs.put("Intake_Down1_P", 0.04);
		prefs.put("Intake_Down1_I", 0.006);
		prefs.put("Intake_Down1_D", 0.115);
		prefs.put("Intake_Down1_Max", 180.0);
		prefs.put("Intake_Down1_Min", -180.0);
		prefs.put("Intake_Down1_Tolerance", 0.3);
		prefs.put("Intake_Down1_Continuous", 0.0);
		prefs.put("Turn_To_Angle", 90.0);
	}
	
}
