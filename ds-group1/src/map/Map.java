package map;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Map implements Cloneable {

	private ArrayList<ArrayList<Cell>> map;
	private int diamonds = 0;
	
	/**
	 * Constructor
	 * @param path Path to filename
	 * @throws FileNotFoundException 
	 */
	public Map(String path) throws FileNotFoundException {
		map = new ArrayList<ArrayList<Cell>>();
		this.load(path);
	}
	
	public Cell getXY(int x, int y){
		return map.get(y - 1).get(x - 1);
	}
	
	public Cell setXY(int x, int y, Cell cell){
		return map.get(y - 1).set(x - 1, cell);
	}
	
	public int getWidth() {
		if(map.isEmpty()){
			return 0;
		}else
			return map.get(0).size();
	}
	
	public int getHeight() {
		return map.size();
	}
	
	public void load(String path) throws FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		
		ArrayList<String> maporig = new ArrayList<String>();
		
		String line = null;
		try {
			while((line = in.readLine()) != null){
				maporig.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int x = 0;
		for(int i = maporig.size() - 1; i >= 0; i--){	
			map.add(new ArrayList<Cell>());
			
			char[] lineChars = maporig.get(i).toCharArray();
			
			for (char c : lineChars) {
				switch (c) {
				case '#':
					map.get(x).add(new Wall());
					break;
				case '.':
					map.get(x).add(new Earth());
					break;
				case 'R':
					map.get(x).add(new Robot());
					break;
				case 'x':
					map.get(x).add(new Diamond());
					diamonds++;
					break;
				case 'L':
					map.get(x).add(new ClosedLift());
					break;
				case 'O':
					map.get(x).add(new OpenLift());
					break;
				case '*':
					map.get(x).add(new Rock());
					break;
				case ' ':
					map.get(x).add(new Empty());
					break;
				default:
					break;
				}
			}
			x++;
		}
	}
	
	public void update() throws RobotDestroyedException {
		LinkedList<Cell> fallingRocks = new LinkedList<Cell>();
		
		for(int y = 1; y <= getHeight(); y++){
			for(int x = 1; x <= getWidth(); x++) {
				Cell cell = getXY(x, y);
				
				if(cell instanceof Rock && y > 1) {
					Rock rock = (Rock)cell;
					Cell below = getXY(x, y - 1);
					if(below instanceof Empty) { // the rock falls
						setXY(x, y - 1, rock);
						setXY(x, y, new Empty());
						rock.setFalling(true);
					}
					else if(below instanceof Robot && rock.isFalling()) { // robot destroyed
						throw new RobotDestroyedException("The robot was destroyed!");
					}
					else if(((below instanceof Rock) || (below instanceof Diamond)) && y > 1) { // rock can slip
						if(getXY(x + 1, y) instanceof Empty) { // slip right
							Cell other = getXY(x + 1, y - 1);
							if(other instanceof Empty) { // slip
								setXY(x + 1, y - 1, rock);
								setXY(x, y, new Empty());
								rock.setFalling(true);
								fallingRocks.add(rock);
							}
							else if(other instanceof Rock && fallingRocks.contains(other)) { // "fuse" rock
								setXY(x, y, new Empty());
							}
							else
								rock.setFalling(false);
						}
						else if(getXY(x - 1, y) instanceof Empty) { // slip left
							Cell other = getXY(x - 1, y - 1);
							if(other instanceof Empty) { // slip
								setXY(x - 1, y - 1, rock);
								setXY(x, y, new Empty());
								rock.setFalling(true);
								fallingRocks.add(rock);
							}
							else if(other instanceof Rock && fallingRocks.contains(other)) { // "fuse" rock
								setXY(x, y, new Empty());
							}
							else
								rock.setFalling(false);
						}
						else
							rock.setFalling(false);
					}
					else {
						rock.setFalling(false);
					}
				}
			}
		}
		
		//If there are no diamonds, open lifts
		if(noDiamonds()){
			OpenLifts();
		}
	}

	public String print() {
		String output = "";
		for(int y = 1; y <= getHeight(); y++) {
			String output_line = "";
			for(int x = 1; x <= getWidth(); x++) {
				Cell cell = getXY(x, y);
				output_line += cell.print();
			}
			output = output_line + "\n" + output;
		}
			
		return output;
	}
	
	public boolean makeMove(String direction) throws EndOfMapException{
		
		Point robotPosition = getRobotPosition();
		Robot robot = (Robot)getXY(robotPosition.x+1, robotPosition.y+1);
		
		Point destination = null;
		
		switch (direction.toLowerCase()){
			case "u":
				destination =  new Point(robotPosition.x, robotPosition.y+1);
				break;
			case "l":
				destination =  new Point(robotPosition.x-1, robotPosition.y);
				break;
			case "d":
				destination =  new Point(robotPosition.x, robotPosition.y-1);
				break;
			case "r":
				destination =  new Point(robotPosition.x+1, robotPosition.y);
				break;
			case "w":
				return true;
			case "a":
				throw new EndOfMapException("You aborted the diamond-finding activity. Score: " + robot.getCurrentScore());
			default:
				return false;
		}
		
		Cell object = map.get(destination.y).get(destination.x);
		
		if(object instanceof OpenLift){
			map.get(robotPosition.y).set(robotPosition.x, new Empty());
			robot.addStep();
			throw new EndOfMapException("Congratulations, map concluded!Score: " + robot.getFinalScore());
			// mudar de mapa
		}
		else if(object instanceof Earth){
			map.get(robotPosition.y).set(robotPosition.x, new Empty());
			map.get(destination.y).set(destination.x, robot);
			robot.addStep();
			return true;
		}
		else if(object instanceof Diamond){
			map.get(robotPosition.y).set(robotPosition.x, new Empty());
			map.get(destination.y).set(destination.x, robot);
			robot.addStep();			
			robot.addDiamond();
			diamonds--;
			
			return true;
		}
		else if(object instanceof Empty){
			map.get(robotPosition.y).set(robotPosition.x, new Empty());
			map.get(destination.y).set(destination.x, robot);
			robot.addStep();
			return true;
		} 
		else if(object instanceof Rock){
			/*
			 * Push rock to left
			 */
			if(direction.toLowerCase().equals("l")){
				Cell leftToRock =  map.get(robotPosition.x-2).get(robotPosition.y);
				if(leftToRock instanceof Empty){
					map.get(robotPosition.y).set(robotPosition.x, new Empty());
					map.get(destination.y).set(destination.x, robot);
					map.get(robotPosition.y).set(robotPosition.x-2, new Rock());
					robot.addStep();
					return true;
				}
				return false;
			}
			
			/*
			 * Push rock to right
			 */
			else if(direction.toLowerCase().equals("r")){
				Cell rightToRock =  map.get(robotPosition.x+2).get(robotPosition.y);
				if(rightToRock instanceof Empty){
					map.get(robotPosition.y).set(robotPosition.x, new Empty());
					map.get(destination.y).set(destination.x, robot);
					map.get(robotPosition.y).set(robotPosition.x+2, new Rock());
					robot.addStep();
					return true;
				}		
			}
			return false;
		}
		
		return false;
	}
	
	public boolean noDiamonds(){
		return diamonds == 0;
	}
	
	private void OpenLifts(){
		for(int y = 1; y <= getHeight(); y++){
			for(int x = 1; x <= getWidth(); x++){
				if(getXY(x, y) instanceof ClosedLift)
					setXY(x, y, new OpenLift());
			}
		}
	}

	public Point getRobotPosition(){
		for(int i=0; i < map.size(); i++)
			for(int j=0; j < map.get(i).size(); j++){
				if(map.get(i).get(j) instanceof Robot)
					return new Point(j,i);
			}
		return null;
	}
	
	public Point convert0BasedTo1Based(Point p){
		return new Point(p.x + 1, p.y + 1);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Map result = (Map)super.clone();
		result.map = new ArrayList<ArrayList<Cell>>();
		for(int i = 0; i < map.size(); i++) {
			ArrayList<Cell> line = new ArrayList<Cell>();
			for(int j = 0; j < map.get(i).size(); j++) {
				line.add((Cell)map.get(i).get(j).clone());
			}
			result.map.add(line);
		}
		return result;
	}
	
	public LinkedList<Point> getDiamonds(){
		LinkedList<Point> temp = new LinkedList<Point>();
		
		for(int y = 1; y <= getHeight(); y++){
			for(int x = 1; x <= getWidth(); x++){
				if(getXY(x, y) instanceof Diamond)
					temp.add(new Point(x, y));
			}
		}
		return temp;
	}
	
	public LinkedList<Point> getOpenLifts(){
		LinkedList<Point> temp = new LinkedList<Point>();
		
		for(int y = 1; y <= getHeight(); y++){
			for(int x = 1; x <= getWidth(); x++){
				if(getXY(x, y) instanceof OpenLift)
					temp.add(new Point(x, y));
			}
		}
		return temp;
	}
}
