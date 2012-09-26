package layout;

import java.util.Scanner;

public class Input {
	private static Scanner scanner = new Scanner(System.in);
	
	// KEY CODES
	public static final int LEFT = 0;
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int WAIT = 4;
	public static final int INVALID_MOVE = -1;
	
	// AVAILABLE KEYBOARD KEYS
	public static String LEFT_KEY = "l";
	public static String UP_KEY = "u";
	public static String RIGHT_KEY = "r";
	public static String DOWN_KEY = "d";
	public static String WAIT_KEY = "w";
	
	// READS INPUT FROM KEYBOARD AND RETURNS KEY CODE
	public static int checkInput()
	{
		String input = scanner.next();
		if(input.equals(LEFT_KEY))
			return LEFT;
		else if(input.equals(UP_KEY))
			return UP;
		else if(input.equals(RIGHT_KEY))
			return RIGHT;
		else if(input.equals(DOWN_KEY))
			return DOWN;
		else if(input.equals(WAIT_KEY))
			return WAIT;
		
		return INVALID_MOVE;	
	}
}
