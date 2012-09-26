import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Mine {
	ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
	private int diamonds = 0;
	
	public Mine(String s) {
		String[] lines;
		lines = s.split("\n");
		
		for(int i = 0; i < lines.length; i++) {
			map.add(parseString(lines[i]));
		}
	}
	
	public Mine(File file) {
		readMap(file);
		//ytest
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
		char[] chararray = s.toCharArray();
		ArrayList<Character> r = new ArrayList<Character>();
		for(int i = 0; i < chararray.length; i++) {
			if(chararray[i] != '\n')
				r.add(chararray[i]);
		}
		return r;
	}
	
	public String toString(){
		String s = "";
		ArrayList<Character> line;
		for (int i = 0; i < map.size(); i++){
			if (i != 0)
				s += "\n";
			
			line = map.get(i);
			
			for (Character character : line)
				s += character;
		}
		return s;
	}
	
	public void print(){
		System.out.print(toString());
		System.out.println("Diamonds: " + diamonds);
	}
	
	
	public char getCell(int line, int col) {
		return (map.get(line)).get(col);
	}
	
	public void setCell(int n, int m, Character c) {
		map.get(n).set(m, c);
	}
	
	/**
	 * Receives the command letter
	 * @param c
	 * @return
	 */
	public boolean move(Character c) {
		c = Character.toLowerCase(c);
		
		switch (c){
		
		case 'l':
			return move(0, -1);
		case 'r':
			return move(0, 1);
		case 'd':
			return move(-1, 0);
		case 'u':
			return move(1, 0);
		case 'w':
			return true;
		}
		return false;
	}
	
	/**
	 * Receives
	 * @param delta line
	 * @param delta col
	 * @return
	 */
	public boolean move(int line, int col) {
		Position currentPosition = getRobotPosition();
		char z = '#';
		
		// verifica se se vai mover para fora das array lists	
		try {
			getCell(currentPosition.x, currentPosition.y);
			z = getCell(currentPosition.x + line, currentPosition.y + col);
		} catch (IndexOutOfBoundsException e){
			return false;
		}
		
		z = Character.toLowerCase(z);
		
		if(z == 'x')
			diamonds++;
		else if(!(z == ' ' || z == '.' || z== ' '))
			return false;
		
		//se o movimento for valido executa-o!
		setCell(currentPosition.x, currentPosition.y, ' ');
		setCell(currentPosition.x + line, currentPosition.y + col, 'R');
		
		return true;
	}
	
	public void updateMap() {
		for(int i = 0 ; i < map.size(); i++) {
			for(int j = map.get(i).size() - 1; j >= 0; j--) {
				if(getCell(i, j) == '*' && getCell(i+1, j) == ' ') {
					setCell(i, j, ' ');
					setCell(i+1, j, '*');
				}
				else if(getCell(i, j) == '*' && getCell(i+1, j) == 'R') {
					System.out.println("Your Robot is dead...");
					System.exit(0);
				}
			}
		}
	}
	
	public boolean levelUp() {
		return (map.toString().indexOf("x") == -1);
	}
	
	// returns robot position (l, c)
	// when there's no robot, returns (-1, -1)
	public Position getRobotPosition() {
		for (int i=0; i<map.size(); i++) {
			for (int j=0; j<map.get(i).size(); j++) {
				if (map.get(i).get(j).equals('R')) {
					return new Position(i, j);
				}

			}
		}
		return new Position(-1, -1);
	}
	
	public class Position {
	
		public final int x;
		public final int y;
		
		Position(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	// TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
}
