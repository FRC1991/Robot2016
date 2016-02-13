
package org.usfirst.frc.team1991.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team1991.robot.Robot;

/**
 *
 */
public class Read extends Command {
	
	boolean finished = false;
	
	String dir;
	
	File file;
	FileReader fr;
	BufferedReader br;
	Map<String, String> prefs;
    public Read(String dir) {
    	this.dir = dir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	prefs = new HashMap<>();
    	try{
    		file = new File(dir);
    		System.out.println(dir);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
  		Robot.prefs.setPrefs(readPrefs(prefs));
  		finished = true;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
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
  	
}
