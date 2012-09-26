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
		map = new Map(INPUT_FILE);
		
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
			map.validate();
			
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
