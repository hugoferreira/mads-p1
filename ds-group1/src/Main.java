import java.util.Scanner;

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

	public Main() {
		/*
		 * Create Map
		 */
		map = new Map(INPUT_FILE);
		
		/*
		 * Start the game cycle
		 */
		while(true) {
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
			 * Ask user to show and validate the move
			 */
			boolean valid_move = true; 
			do {
				System.out.print("Action?");
				Scanner in = new Scanner(System.in);
			    String input = in.nextLine();

			    valid_move = map.validate(input);
				if (!valid_move) {
					System.out.println("Invalid move");
				}
				
			} while(!valid_move);
			
			/*
			 * Update map
			 */
			try {
				map.update();
				
			}
			catch (Exception e) {
				
			}
		}
		
		
		
		/*
		 * 
		 */
	}
}
