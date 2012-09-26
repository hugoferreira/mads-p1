import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import com.sun.corba.se.impl.orbutil.closure.Constant;


public class Mine {
	
	private static Tab tab;
	
	public Mine(){
		
		tab = new Tab();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		while(true){ // verify if there are still diamons left
			
			System.out.print("Direction to move:");
			// wait for input
			Scanner scan = new Scanner (System.in);
			char userInput = scan.next().charAt(0);
			
			//TODO: robot's movement
			
			//TODO: tab update
			
			
		}
	}
	

	public void robotMove(Point pos, char move){
		
		Point new_pos = (Point) pos.clone();
		switch(move){
		
			case 'l':
				new_pos.x--;
				break;
			case 'r':
				new_pos.x++;
				break;
			case 'u':
				new_pos.y++;
				break;
			case 'd':
				new_pos.y--;
				break;
			default: // equivalent to the 'w' (wait) movement
				break;
				
		}
		
		if(validateMove(new_pos) && !pos.equals(new_pos)){

			//TODO: change robots position in tab object
		}
	}
	
	public boolean validateMove(Point pos){
		
		if(!validPosition(pos))
			return false;
		
		char item = tab.getPoint(pos.x, pos.y);
		if(item == Constants.DIAMOND || item == Constants.EMPTY ||
				item == Constants.EARTH || item == Constants.OPEN_LIFT)
			return true;
		
		return false;
	}
	
	public boolean validPosition(Point pos){
		
		if(pos.x < 1 || pos.y < 1)
			return false;
		
		//TODO: verify max values
		
		return true;
	}
	
	public void update(){
	
		TreeMap<Point, Character> temp_tab = new TreeMap<Point, Character> (tab.getmap());
		for(Point p : temp_tab.keySet()){
			
			Point above_p = new Point(p.x, p.y-1);
			if(validPosition(above_p) && temp_tab.get(above_p) != Constants.EMPTY){
				
				temp_tab.put(p, Constants.EMPTY);
				temp_tab.put(above_p, Constants.ROCK);
			}
		}
		
		//TODO: update map with temporary one
	}
	
}
