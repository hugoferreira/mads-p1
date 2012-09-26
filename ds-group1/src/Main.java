import java.io.FileNotFoundException;

import map.Map;


public class Main {

	public static final String INPUT_FILE = "samples/example1.map";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
	
	/**
	 * Params
	 */
	private Map map;

	public Main() {
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
		
		while(true) {
			/*
			 * Ask user to show
			 */
			
			/*
			 * Validate move and do it
			 */
			//map.validate();
			
			/*
			 * Update map
			 */
			map.update();
		}
		
		
		
		/*
		 * 
		 */
	}
}
