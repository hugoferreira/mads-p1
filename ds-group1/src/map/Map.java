package map;

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
		return map.get(x - 1).get(y - 1);
	}
	
	public int getWidth() {
		if(map.isEmpty())
			return 0;
		else
			return map.get(0).size();
	}
	
	public int getHeight() {
		return map.size();
	}
	
	public void load(String path) throws FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader(path));
		
		String line = null;
		try {
			int x = 0, y = 0;
			while((line = in.readLine()) != null){
				
				char[] lineChars = line.toCharArray();
				y = 0;
				for (char c : lineChars) {
					
					switch (c) {
					case '#':
						map.get(x).set(y, new Wall());
						break;
					case '.':
						map.get(x).set(y, new Earth());
						break;
					case 'R':
						map.get(x).set(y, new Robot());
						break;
					case 'x':
						map.get(x).set(y, new Diamond());
						break;
					case 'L':
						map.get(x).set(y, new ClosedLift());
						break;
					case 'O':
						map.get(x).set(y, new OpenLift());
						break;
					case '*':
						map.get(x).set(y, new Rock());
						break;
					case ' ':
						map.get(x).set(y, new Empty());
						break;
					default:
						break;
					}
					y++;
				}
				x++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		for(int y = 1; y <= getHeight(); y++)
			for(int x = 1; x <= getWidth(); x++) {
				Cell cell = getXY(x, y);
				if(cell instanceof map.Rock) {
					// TODO
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
}
