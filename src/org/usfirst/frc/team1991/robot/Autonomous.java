package org.usfirst.frc.team1991.robot;



public class Autonomous {
	
	//Reader read = new Reader(System.getProperty("user.dir"));
	int defense = 1;
	int position = 1;
	boolean shoot = false;
	String[] values;
	
	
	public Autonomous(){
		//values = read.readAut();
		try{
			position = Integer.parseInt(values[0]);
			defense = Integer.parseInt(values[1]);
			if(values[2].equals("true")){
				shoot = true;
			}
			
			
		}catch(Exception e){
			
		}
	}
}
