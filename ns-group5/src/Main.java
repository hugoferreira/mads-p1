import java.util.Scanner;


public class Main {
	
	public static Mine mine = new Mine("");
	public static Scanner scanner = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// MAIN CICLE
		while(true) {
			
			// exit condition
			
			// show map
			mine.print();
			
			// ask user for a command
			//String command = scanner.nextLine();
			//mine.move(command.)
			
			// update map
			mine.updateMap();
			
		}

	}

}
