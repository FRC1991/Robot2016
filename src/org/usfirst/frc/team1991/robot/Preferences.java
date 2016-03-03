package org.usfirst.frc.team1991.robot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Preferences {


	public HashMap<String, Double> prefs;
	HashMap<String, Double> tempPrefs;
	public HashMap<String, Double> TempPrefs;

	File file;
	FileReader fr;
	BufferedReader br;
	int linecounter = 0;

	@SuppressWarnings("unchecked")
	public Preferences(String dir){
		tempPrefs= new HashMap<String, Double>();
		prefs = new HashMap<String,Double>();
		setPrefs();
		TempPrefs = (HashMap<String, Double> )prefs.clone();
		try{
			file = new File(dir);
			System.out.println("Preferences directrory: " + dir);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		readPrefs();
		setValues();
	}
	//Gets values from readPrefs() and set prefs
	public void setValues(){
		readPrefs();
		this.linecounter = 0;
		double value;
		for(String s: TempPrefs.keySet()) {
			String key = s;
			int count = 0;
			if(tempPrefs.containsKey(key)) {
				//System.out.println(count);
				count++;
				value = tempPrefs.get(key);
				prefs.remove(key);
				prefs.put(key, value);
			}
		}
		printPrefs(prefs);
	}
	//Reads user Preferences from a text file written in AakashLang
	public void readPrefs(){
		try{
			try{
				fr = new FileReader(file); 
			}catch(Exception e){
				e.printStackTrace();
			}
			
			br = new BufferedReader(fr); 
			String line;
			String type = null;
			String subtype = null;
			while((line = br.readLine()) != null)
			{ 
				linecounter++;
				
				if(line != "" && line.length() > 5){
					  line = line.trim();
					  String key;
					  String value;
					  String variable;
					  String[]texts = line.split(" ");
					  
					 if(line.substring(0,1).equals("[") && 
							line.substring(line.length() - 1).equals("]")){
					    	type = line.substring(1, line.length()-1);
					    	errorCheck(type);
						
					    }else if(line.substring(0,1).equals("(") && 
							line.substring(line.length() - 1).equals(")")){
					    	subtype = line.substring(1, line.length()-1);
					    	errorCheck(subtype);
					    }
					  
					  
					  	if(type != null){
					  		 if(type.equals("END")){
							    	type = null;
									subtype = null;
							    }
					  	}
					    if(type != null && texts.length >= 3){
					    	 variable = texts[0];
					    	if(subtype.equals("PID")){
					    		String[] moreTexts = texts[2].split(",");
					    		key = type + "_" + variable +  "_";
					    		
					    		addValue(key + "P", moreTexts[0]);
					    		addValue(key + "I", moreTexts[1]);
					    		addValue(key + "D", moreTexts[2]);
					    		addValue(key + "Tolerance", moreTexts[3]);
					    		addValue(key + "Min", moreTexts[4]);
					    		addValue(key + "Max", moreTexts[5]);
					    		if(moreTexts[06].equals(false)){
					    			addValue(key + "Continuous", "0.0");
					    			
					    		}else{
					    			addValue(key + "Continuous", "1.0");
					    		}
					    		
					    		
					    	}else{
					    		key = type + "_" + subtype +  "_" + variable;
					    		value = texts[2];
					    		addValue(key, value);
					    	}
					    }
					}
			  
			    
				} 
			br.close();
			fr.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void printPrefs(HashMap<String, Double> prefs){
		System.out.println("\n");
		ArrayList<String> list = new ArrayList<>();
		for(String s:prefs.keySet()){
			list.add(s + ": " + prefs.get(s) + "\n");
			list.sort(new Comparator<String>(){
				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return o1.compareTo(o2);
				}
			});
		}
		for(String s:prefs.keySet()){
			System.out.println(s + ": " + prefs.get(s) + "\n");
			
		}
		
	}
	public void addValue(String key, String value){
		errorCheck(value, ".", "-");
		double val = -1001;
		try{
			val = Double.parseDouble(value);
			if(val != -1001){
    			tempPrefs.put(key, val);
			}
		}catch(Exception e){
			System.out.println("ERROR, the following prefs value is not a double. Line: " 
					+ linecounter);
			System.out.println(key + " : " + value);
			System.out.println("Using default values");

		}
	}

	//Gets a value from the HashMap prefs based on key
	public double get(String key) {
		if(prefs.containsKey(key)) {
			return prefs.get(key);
		}
		else{
			return 0.0;
		}
	}

	//Sets default preference values
	public void setPrefs() {
		prefs.put("DriveTrain_Speed_Multiplier", 0.5);
		prefs.put("DriveTrain_Speed_Rotation", 0.5);
		prefs.put("DriveTrain_Offset_Left", 0.0);
		prefs.put("DriveTrain_Offset_Right", 0.0);
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
	public void check(String line, String exception, String exception2) throws Exception{
		String forbid = "{ } [ ] | \\ ! @ # $ % ^ & * ( ) _ + - = : ; \" < , > . ? ' ";
		String[] forbidden = forbid.split(" ");
		for(int i = 0; i < forbidden.length; i++){
			if(!forbidden[i].equals(exception) && !forbidden[i].equals(exception2)){
				if(line.contains(forbidden[i])) throw new Exception();
			}
		}
	}
	public void errorCheck(String line){
		try{
			check(line, null, null);
		}catch(Exception e){
			System.out.println("ERROR, Check syntax, Illegal characters. Line: " 
					+ linecounter);
			System.out.println("'" + line + "'");
			System.out.println("Using default values");
		}
		
	}
	public void errorCheck(String line, String exception, String exception2){
		try{
			check(line, exception, exception2);
		}catch(Exception e){
			System.out.println("ERROR, Check syntax, Illegal characters. Line: " 
					+ linecounter);
			System.out.println("Using default values");
		}
		
	}

}
