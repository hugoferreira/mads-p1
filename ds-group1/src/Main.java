import java.util.Scanner;
import java.util.Stack;
import java.io.FileNotFoundException;
import map.Map;

public class Main {

	public static final String INPUT_FILE = "samples/example1.map";
	public int game_step = 1;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Main main = new Main();
	}
	
	/**
	 * Params
	 */
	private Map map;
	private Stack<Map> map_stack;

	public Main() {
		map_stack = new Stack<Map>();
		/*
		 * Create Map
		 */
		try {
			map = new Map(INPUT_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		 * Start the game cycle
		 */
		boolean valid_step = true;
		do {
			/*
			 * Update game step
			 */
			game_step++;
			
			/*
			 * Display board
			 */
			System.out.println("Map for step " + game_step);
			System.out.println(map.print());
			
			/*
			 * Ask user for undo or continue
			 */
			System.out.print("Do you want to continue play? (yes/no)");
			Scanner in = new Scanner(System.in);
		    String input = in.nextLine();
			switch (input.toLowerCase()) {
			case "no":
				map = map_stack.pop();
				continue;
			default:
				valid_step = execute_step();
			}
		    
			//valid_step = execute_step();
			
			/*
			 * Save map
			 */
			//map_stack.push(map);
			
		} while (valid_step);
		
		System.out.println("GAME OVER");
	}
	
	private boolean execute_step() {
		/*
		 * Ask user to show and validate the move
		 */
		boolean valid_move = true; 
		do {
			System.out.print("Action?");
			Scanner in = new Scanner(System.in);
		    String input = in.nextLine();

		    valid_move = map.makeMove(input);
			if (!valid_move) {
				System.out.println("Invalid move");
			}
			
		} while(!valid_move);
		
		/*
		 * Update map
		 */
		try {
			map.update();
			// TODO
		}
		catch (Exception e) {
			return false;
		}
		
		/*
		 * Everything was OK, so return true
		 */
		return true;
	}
}
