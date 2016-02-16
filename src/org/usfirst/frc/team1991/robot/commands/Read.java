
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
	HashMap prefs;
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
  		Robot.drivetrain.setValues(readPrefs(prefs));

  		finished = true;

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      	return finished; 
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
    public Double[] readPrefs(HashMap values){
    		Double[] speeds = new Double[5];
		try{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String temp;
			String[] vals;
			String value;
			String key;
			while((temp = br.readLine()) != null){
				//System.out.println(temp);
				if(temp.substring(0, 1).equals("#")){

					vals = temp.split(" ");
					key = vals[0].substring(1);
					value = vals[2];
					//System.out.println(value);
					//System.out.println(key);
					values.put(key, value);
				}

			}
		}catch(Exception e){

		}
		try{
			speeds[0] = Double.parseDouble((String) values.get("SMultiplier"));
			speeds[3] = Double.parseDouble((String) values.get("SRotate"));
			speeds[1] = Double.parseDouble((String) values.get("SLeft"));
			speeds[2] = Double.parseDouble((String) values.get("SRight"));

			System.out.println(speeds[0] + " " + speeds[1]);
		}catch(Exception e){
			e.printStackTrace();
		}

		return speeds;
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
