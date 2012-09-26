import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import com.sun.corba.se.impl.orbutil.closure.Constant;


public class Mine {

	private Tab tab;
	private boolean running, last;

	public Mine(Tab tab_incoming){

		tab = tab_incoming;
		running = true;
		last = false;
		cycle();
	}

	public Mine(String filename){
		tab = new Tab();
		tab.readFromFile(filename);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public void cycle(){
		// TODO Auto-generated method stub

		print_menu();
		do{ // verify if there are still diamons left

			//last = !running;

			tab.printTab();
			System.out.println();
			System.out.print("Direction to move:");
			// wait for input
			Scanner scan = new Scanner (System.in);
			char userInput = scan.next().charAt(0);

			if(!checkValid(userInput))
				continue;

			robotMove(userInput);

			// tab update
			update();


		}while(running);// || !last);
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


	public void robotMove(char move){

		Point pos = tab.robotPos;
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
		default: // equivalent to the 'w' (wait) movement
			break;

		}

		if(validateMove(new_pos) && !pos.equals(new_pos)){

			tab.change(pos.x, pos.y, Constants.EMPTY);
			tab.change(new_pos.x, new_pos.y, Constants.ROBOT);
			tab.robotPos = new_pos;

			if(tab.nDiam <= 0){

				Point lpos = tab.lPos;
				tab.change(lpos.x, lpos.y, Constants.OPEN_LIFT);
			}
			if(tab.getPoint(new_pos.x, new_pos.y) == Constants.OPEN_LIFT)
				running = false;
		}
	}

	public boolean validateMove(Point pos){

		if(!validPosition(pos))
			return false;

		char item = tab.getPoint(pos.x, pos.y);

		if(item == Constants.ROCK){
			if(tab.robotPos.x == pos.x-1){
				if(((pos.x+1) <= tab.getXMax()) && tab.getPoint(pos.x+1,pos.y)==Constants.EMPTY)
					return true;
			}
			if(tab.robotPos.x == pos.x+1){
				if(((pos.x-1) > 1) && tab.getPoint(pos.x-1,pos.y)==Constants.EMPTY)
					return true;   
			}
		}

		if(item == Constants.DIAMOND || item == Constants.EMPTY 
				|| item == Constants.EARTH || item == Constants.OPEN_LIFT){

			return true;
		}

		return false;
	}

	public boolean validPosition(Point pos){

		if(pos.x < 1 || pos.y < 1)
			return false;

		if(pos.x > tab.getXMax() || pos.y > tab.getYMax())
			return false;

		return true;
	}

	public void update(){


		ArrayList<Point> new_positions = new ArrayList<Point>();

		for(int i = 1; i <= tab.getXMax(); i++){

			for(int j = 1; j <= tab.getYMax(); j++){

				if(tab.getPoint(i,j) == Constants.ROCK){

					Point new_pos = new Point();

					if(validPosition(new Point(i,j-1)) && tab.getPoint(i,j-1) == Constants.EMPTY){

						tab.change(i,j, Constants.EMPTY);
						tab.change(i, j-1, Constants.ROCK);
						new_pos.x = i;
						new_pos.y = j-1;
					}
					else if(tab.getPoint(i,j-1) == Constants.ROCK || tab.getPoint(i,j-1) == Constants.DIAMOND){

						if(validPosition(new Point(i+1,j-1)) && tab.getPoint(i+1,j) == Constants.EMPTY
								&& (tab.getPoint(i+1,j-1) == Constants.EMPTY || new_positions.contains(new Point(i+1,j-1)))){

							tab.change(i,j, Constants.EMPTY);
							tab.change(i+1, j-1, Constants.ROCK);
							new_pos.x = i+1;
							new_pos.y = j-1;
							new_positions.add(new Point(i+1, j-1));
						}
						else if(validPosition(new Point(i-1,j-1)) && tab.getPoint(i-1,j) == Constants.EMPTY
								&& (tab.getPoint(i-1,j-1) == Constants.EMPTY || new_positions.contains(new Point(i-1,j-1)))){

							tab.change(i,j, Constants.EMPTY);
							tab.change(i-1, j-1, Constants.ROCK);
							new_pos.x = i-1;
							new_pos.y = j-1;
							new_positions.add(new Point(i-1, j-1));
						}
					}

					if(tab.robotPos.equals(new_pos))
						running = false;
				}

			}

		}
	}

}
