package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

	private ArrayList<ArrayList<Cell>> map;
	
	public Map(int sizex, int sizey){
		map = new ArrayList<ArrayList<Cell>>(sizex);
		for(int i = 0; i < sizex; i++){
			map.set(i, new ArrayList<Cell>(sizey));
		}
	}
	
	public Cell getXY(int x, int y){
		return map.get(x - 1).get(y - 1);
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

}
