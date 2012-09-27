package main;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Map implements Cloneable {

	public Pos[][] map;
	
	public Map() {
		
	}
	
	public Map clone()
	{
		Map cm = new Map();
		cm.map = new Pos[map.length][map[0].length];

		for(int i = 0; i < map.length; ++i)
		for(int j = 0; j < map[0].length; ++j)
		cm.map[i][j] = this.map[i][j];

		return cm;	
	}
	
	public Map(String filename) throws Exception {
		Scanner sc = new Scanner(new File(filename));
		
		Vector<String> buf = new Vector<String>();
		
		while(sc.hasNext()){
			String b=sc.nextLine().trim();
			if(b.length()!=0)
				buf.add(b);
		}
		sc.close();
		
		map = new Pos[buf.size()][];
		
		for(int line = 0; line<buf.size(); line++){
			String s = buf.get(line);
			
			map[line] = new Pos[s.length()];
			
			for(int i=0; i<s.length(); i++){
				Pos c = convert(s.charAt(i));
				if(c != null)
					map[line][i] = c;
				else{
					System.out.println(s.charAt(i));
					throw new Exception();
				}
			}
		}
	}
	
	public Pos convert(char c){
		
		switch(c){
		case 'R':
			return Pos.ROBOT;
		case 'L':
			return Pos.LIFT_C;
		case 'O':
			return Pos.LIFT_O;
		case '.':
			return Pos.EARTH;
		case '#':
			return Pos.WALL;
		case 'x':
			return Pos.DIAMOND;
		case ' ':
			return Pos.EMPTY;
		case '*':
			return Pos.ROCK;
		default:
			return null;
		}
		
	}
	public int getRobotX(){
		for(int i=0; i<map.length; i++)
		for(int j=0; j<map[i].length; j++)
		if(map[i][j] == Pos.ROBOT)
		return j;
		return -1;
		}
		public int getRobotY(){
		for(int i=0; i<map.length; i++)
		for(int j=0; j<map[i].length; j++)
		if(map[i][j] == Pos.ROBOT)
		return i;
		return -1;}
}
