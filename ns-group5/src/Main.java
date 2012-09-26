import java.io.File;
import java.util.Scanner;


public class Main {
	
<<<<<<< HEAD
	public static Mine mine = new Mine(new File("example1.map"));
=======
	public static Mine mine = new Mine("######\n#. *R#\n#  x.#\n#x * #\nL  .x#\n######");
>>>>>>> 7b42250f5796139b45febb55ef4f15cf7f22e444
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
			String command = scanner.nextLine();
			if (!(command.toLowerCase().charAt(0) == 'w')) {
				if (!mine.move(command.charAt(0))) {
					System.out.println("Invalid Command");
				}
			}
			
			// update map
			mine.updateMap();
			
		}

	}

}
