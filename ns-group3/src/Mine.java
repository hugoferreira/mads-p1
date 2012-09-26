import java.awt.Point;
import java.io.IOException;
import java.util.Scanner;


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
			
			// robot's movement
			
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
	

	public boolean robotMove(Point pos, char move){
		
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
			default:
				return false;
				
		}
		
		return false;
	}
	
	public boolean validateMove(Point pos){
		
		if(!validPosition(pos))
			return false;
	
		
		
		return false;
	}
	
	public boolean validPosition(Point pos){
		
		if(pos.x < 1 || pos.y < 1)
			return false;
		
		//TODO: verify max values
		
		return true;
	}
	

}
