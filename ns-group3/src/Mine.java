import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;


public class Mine {
	
	private Tab tab;
	
	public Mine(Tab tab_incoming){
		
		tab = tab_incoming;
		cycle();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void cycle(){
		// TODO Auto-generated method stub

		print_menu();
		while(true){ // verify if there are still diamons left
			
			
			tab.printTab();
			System.out.println();
			System.out.print("Direction to move:");
			// wait for input
			Scanner scan = new Scanner (System.in);
			char userInput = scan.next().charAt(0);
			
			if(!checkValid(userInput))
				continue;
			
			robotMove(userInput);
			
			// tab update
			
			
		}
	}
	
	public static boolean checkValid(char c){
		
		char opt = Character.toLowerCase(c);
		if(opt!=Constants.UP && opt!=Constants.DOWN && 
				opt!=Constants.LEFT && opt!=Constants.RIGHT && 
				opt!=Constants.WAIT)
			return false;
		
		return true;
	}
	
	public static void print_menu(){
		System.out.println("*************");
		System.out.println("* NS-GROUP3 *");
		System.out.println("*************");
	}
	

	public void robotMove(char move){
		
		Point pos = tab.robotPos;
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

		System.out.println(pos + " - " + new_pos);
		if(validateMove(new_pos) && !pos.equals(new_pos)){

			tab.change(pos.x, pos.y, Constants.EMPTY);
			tab.change(new_pos.x, new_pos.y, Constants.ROBOT);
			tab.robotPos = new_pos;
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
		
		if(pos.x > tab.getXMax() || pos.y > tab.getYMax())
			return false;
		
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
