import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Mine {
	ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
	
	public Mine(String s) {
		String[] lines;
		lines = s.split("\n");
		
		for(int i = 0; i < lines.length; i++) {
			map.add(parseString(lines[i]));
		}
	}
	
	public Mine(File file) {
		readMap(file);
	}
	
	public void readMap(File file){
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(file);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				map.add(parseString(strLine));
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private ArrayList<Character> parseString(String s) {
		String[] chararray = s.split("\\a");
		ArrayList<Character> r = new ArrayList<Character>();
		for(int i = 0; i < chararray.length; i++) {
			r.add(chararray[i].charAt(i));
		}
		return r;
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
	
	public void print(){
		System.out.print(toString());
	}
	
	
	public char getCell(int line, int col) {
		return '0';
		
	}
	
	public boolean setCell(int n, int m, Character c) {
		return false;
		
	}
	
	public boolean move(Character c) {
		return false;
	}
	
	
	
	public void updateMap() {
		
	}
	
	public boolean levelUp() {
		return false;
	}
	
}
