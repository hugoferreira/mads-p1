package map;

import java.util.ArrayList;

public class Map {

	private ArrayList<ArrayList<Cell>> map;
	
	public Cell getXY(int x, int y){
		return map.get(x - 1).get(y - 1);
	}
	
	public void load(String path){
		
	}

}
