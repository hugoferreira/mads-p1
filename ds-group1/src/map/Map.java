package map;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

	private ArrayList<ArrayList<Cell>> map;
	
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
	
	public void update() {
		for(int y = 1; y <= getHeight(); y++)
			for(int x = 1; x <= getWidth(); x++) {
				Cell cell = getXY(x, y);
				
				if(cell instanceof Rock && y > 1) {
					Cell below = getXY(x, y - 1);
					if(below instanceof Empty) { // rocha cai
						setXY(x, y - 1, cell);
						setXY(x, y, new Empty());
					}
					else if(below instanceof Robot) { // robô destruido
						
					}
				}
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
	
public boolean makeMove(String direction){
		
		Point robotPosition = getRobotPosition();
		
		Point destination = null;
		
		switch (direction.toLowerCase()){
			case "w":
				destination =  new Point(robotPosition.x, robotPosition.y+1);
				break;
			case "a":
				destination =  new Point(robotPosition.x-1, robotPosition.y);
				break;
			case "s":
				destination =  new Point(robotPosition.x, robotPosition.y-1);
				break;
			case "d":
				destination =  new Point(robotPosition.x+1, robotPosition.y);
				break;
			default:
				break;
		}
		
		Cell object = map.get(destination.x).get(destination.y);
		
		if(object instanceof OpenLift){
			map.get(robotPosition.x).set(robotPosition.y, new Empty());
			return true;
			// mudar de mapa
		}
		else if(object instanceof Earth){
			map.get(robotPosition.x).set(robotPosition.y, new Empty());
			map.get(destination.x).set(destination.y, new Robot());
			return true;
		}
		else if(object instanceof Diamond){
			map.get(robotPosition.x).set(robotPosition.y, new Empty());
			map.get(destination.x).set(destination.y, new Robot());
			//TODO somar diamante
			return true;
		}
		else if(object instanceof Empty){
			map.get(robotPosition.x).set(robotPosition.y, new Empty());
			map.get(destination.x).set(destination.y, new Robot());
			//efectuar movimento
			return true;
		}
		
		return false;
	}
	
	

	public Point getRobotPosition(){
		for(int i=0; i<map.size(); i++)
			for(int j=0; j<map.get(i).size(); j++){
				if(map.get(i).get(j) instanceof Robot)
					return new Point(i,j);
			}
		return null;
	}
}
