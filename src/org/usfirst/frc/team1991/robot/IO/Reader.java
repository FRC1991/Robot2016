package org.usfirst.frc.team1991.robot.IO;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Reader {
	
	String dir;
	File file = new File(dir);
	FileReader fr;
	BufferedReader br;
	//Reader class reads files based on directory give, currently used for preferences
	//To be used for autonomous
	public Reader(String dir){
		this.dir = dir;
		
	}
	public Map readPrefs(Map values){
		try{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String temp;
			String[] vals;
			String value;
			String key;
			while((temp = br.readLine()) != null){
				if(temp.substring(0, 1) == "#"){
					vals = temp.split(" ");
					key = vals[0].substring(1);
					value = vals[2];
					values.put(key, value);
				}
				
			}
		}catch(Exception e){
			
		}
		
		return values;
	}
	
	
	//Reads autonomous values from text file
	public String[] readAut(){
		String[] vals = new String[5];
		try{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String temp;
			
			
			while((temp = br.readLine()) != null){
				if(temp.substring(0, 1) == "#"){
					vals = temp.split(" ");
				}
				
			}
		}catch(Exception e){
			
		}
		vals[0] = vals[0].substring(1);
		return vals;
	}
	
	//Outputs Preferences
	public Map getPrefs(){
		Map<String, String> prefs = new HashMap<>();
		prefs = readPrefs(prefs);
		return prefs;
	}
	
}
