import java.util.ArrayList;


public class Mine {
	ArrayList<ArrayList<Character>> map;
	
	public Mine(ArrayList<ArrayList<Character>> map) {
		this.map = map;
	}
	
	public void readMap(){
		
	}
	
	public String toString(){
		String s = "";
		
		for (ArrayList<Character> line : map){
			for (Character character : line)
				s += character;
			s += "\n";
		}
		
		return s;
	}
	
	public Character getCell() {
		return null;
		
	}
	
	public boolean setCell(int n, int m, Character c) {
		return false;
		
	}
	
	public boolean move(int n1, int m1, int n2, int m2) {
		return false;
	}
	
	public void updateMap() {
		
	}
	
	public boolean levelUp() {
		return false;
	}
	
	public void print(){
		System.out.print(toString());
	}
}
