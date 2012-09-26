import ia.AutoPilot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.Point;
import java.io.FileNotFoundException;

import map.EndOfMapException;
import map.Map;
import map.Robot;
import map.RobotDestroyedException;

public class Main {

	public static final String INPUT_FILE = "samples/example1.map";
	public int game_step = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		@SuppressWarnings("unused")
		Main main = new Main();
	}
	
	/**
	 * Params
	 */
	private Map map;
	private ArrayList<Map> map_stack = new ArrayList<Map>();

	@SuppressWarnings("resource")
	public Main() {
		/*
		 * Create Map
		 */
		try {
			map = new Map(INPUT_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.print("Manual or IA? (M or I) ");
		Scanner in = new Scanner(System.in);
	    String input = in.nextLine();
		switch (input.toUpperCase()) {
		case "M":
			play_manual();
			break;
		case "I":
			play_ia();
			break;
		}
	}
	
	private void play_ia() {
		System.out.println("Map for ia");
		System.out.println(map.print());
		AutoPilot autoPilot = new AutoPilot(map);
		
		String path_str = "";
		while(!map.getDiamonds().isEmpty()){
			LinkedList<Point> destinations = map.getDiamonds();
			Point robotPosition = map.convert0BasedTo1Based(map.getRobotPosition());
			LinkedList<Point> path = autoPilot.getPath(robotPosition, destinations);
			path_str += play_ia_walk(robotPosition, path);
		}
		
		LinkedList<Point> openLifts = map.getOpenLifts();
		Point robotPosition = map.convert0BasedTo1Based(map.getRobotPosition());
		LinkedList<Point> path = autoPilot.getPath(robotPosition, openLifts);
		
		if(path.isEmpty()){
			System.out.println("No path to exit found...");
			try {
				path_str += "A";
				map.makeMove("A");
				map.update();
			} catch (EndOfMapException | RobotDestroyedException e) {
				System.out.println(e.getMessage());
			}
			
		}else{
			//Check if it is rewarding get to exit;
			Robot robot = (Robot)map.getXY(robotPosition.x, robotPosition.y);
			if((robot.getFinalScore() - path.size()) - robot.getCurrentScore() <= 0){
				try {
					path_str += "A";
					map.makeMove("A");
					map.update();
				} catch (EndOfMapException | RobotDestroyedException e) {
					System.out.println(e.getMessage());
				}
			}
			path_str += play_ia_walk(robotPosition, path);
		}
		
		
		System.out.println("[Action] " + path_str);
		
	}
	
	private String play_ia_walk(Point robotPosition, LinkedList<Point> path) {
		String path_str = ""; 
		for (Point point : path) {
			String input = AutoPilot.getDirection(robotPosition, point);
			path_str += input + ", ";
			
			try {
				map.makeMove(input);
				map.update();
			} catch (EndOfMapException e) {
				e.printStackTrace();
			} catch (RobotDestroyedException e) {
				e.printStackTrace();
			}
			robotPosition = point;
			
			System.out.println(map.print());
		}
		return path_str;
	}
	
	@SuppressWarnings("resource")
	private void play_manual() {	
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
			 * Save map
			 */
			try {
				map = map_stack.get(game_step-1);
			} catch (IndexOutOfBoundsException e) {
				try {
					map_stack.add((Map) map.clone());
					System.out.println("### New Map");
				} catch (CloneNotSupportedException e1) {
				}
			}
			
			/*
			 * Display board
			 */
			System.out.println("Map for step " + game_step);
			System.out.println(map.print());
			
			/*
			 * Ask user for undo or continue
			 */
			System.out.print("Back, Next or Play? (B,N,P) ");
			Scanner in = new Scanner(System.in);
		    String input = in.nextLine();
			switch (input.toUpperCase()) {
			case "P":
			{
				valid_step = execute_step();
				for(int i = game_step; i < map_stack.size(); i++) {
					map_stack.remove(i);
				}
				break;
			}
			case "B":
			{
				System.out.println("B");
				if (game_step < 2) {
					System.out.println("Sorry, there are no previous moves");
					game_step = game_step - 1;
					continue;
				}
				game_step = game_step - 2;
				continue;
			}
			case "N":
			{
				System.out.println("N");
				if (map_stack.size() == game_step) {
					System.out.println("Sorry, there are no next moves");
					game_step = game_step - 1;
					continue;
				}
				continue;
			}
			}
			
		} while (valid_step);
		
		System.out.println("GAME OVER");
	}
	
	@SuppressWarnings("resource")
	private boolean execute_step() {
		
		try {		
			
			/*
			 * Ask user to show and validate the move
			 */
			boolean valid_move = true; 
			
			do {
				System.out.print("Action (Up, Down, Left, Right)? ");
				Scanner in = new Scanner(System.in);
			    String input = in.nextLine();
	
			    valid_move = map.makeMove(input);
				if (!valid_move) {
					System.out.println("Oooops! Invalid move! Try U, D, L or R");
				}
				
			} while(!valid_move);
			
			
			/*
			 * Update map
			 */
			map.update();
			// TODO
		}
		catch (EndOfMapException e) {
			System.out.println("You finished the map!");
			return false;
		}
		catch (RobotDestroyedException e) {
			System.out.println("Your robot was destroyed!");
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		/*
		 * Everything was OK, so return true
		 */
		return true;
	}
}
