import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	public static Mine mine = new Mine("######\n#. *R#\n#  x.#\n#x * #\nL  .x#\n######");
	public static Scanner scanner = new Scanner(System.in);
	
	public static ArrayList<String> history = new ArrayList<String>();
	public static int actual_map = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// add first tab to history
		history.add(mine.map.toString());
		
		// MAIN CICLE
		while(true) {
			
			// exit condition
			
			// show map
			mine.print();
			
			// ask user for a command
			System.out.print("Input: ");
			String command = scanner.nextLine();
			if (!(command.toLowerCase().charAt(0) == 'w'))
				if (!mine.move(command.charAt(0)))
					if(!undoRedo(command))
						System.out.println("Invalid Command");
			
			// update map
			mine.updateMap();
			
		}

	}
	
	// perform an undo or a redo command
	public static boolean undoRedo(String command) {
		
		if(command.toLowerCase().charAt(0) == 'u') {
			if(!(actual_map == 0)) {
				actual_map--;
				mine = new Mine(history.get(actual_map));
				return true;
			}
		}
		
		if(command.toLowerCase().charAt(0) == 'r') {
			if(!(actual_map == (history.size()-1))) {
				actual_map++;
				mine = new Mine(history.get(actual_map));
				return true;
			}
		}
		
		return false;
	}
	
	public static void undoRedoAddMapHistory() {
		if (actual_map == (history.size()-1)) {
			history.add(mine.map.toString());
			actual_map++;
		}
		else {
			actual_map++;
			history.remove(actual_map);
			history.add(actual_map, mine.map.toString());
		}
	}

}