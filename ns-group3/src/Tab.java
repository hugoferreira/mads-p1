import java.awt.Point;
import java.util.HashMap;


public class Tab {
	private HashMap<Point, Character> map = new HashMap<Point, Character>();
	
	public void change(int x, int y, char f){
		Point position = new Point(x, y);
		map.put(position, f);
	}
	
	public HashMap<Point, Character> getmap(){
		return map;
	}
}
