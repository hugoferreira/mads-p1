package map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Map {
	
	private static final char PLAYER = 'R';
	private static final char ROCK = '*';
	private static final char WALL = '#';
	private static final char EMPTY = ' ';
	private int n;
	private int m;
	private char[][] map;
	private char DIAMOND = 'x';
	
	public void readMap(String filename) {
		
		try{
			
			  FileInputStream fstream = new FileInputStream(filename);
			  FileInputStream fstream2 = new FileInputStream(filename);
			  
			  DataInputStream in = new DataInputStream(fstream);
			  DataInputStream in2 = new DataInputStream(fstream2);
			  
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			  
			  String firstLine = br2.readLine();
			  
			  setN(firstLine.length());
			  setM(1);
			  while(br2.readLine() != null) {
				  
				  setM(getM() + 1);
			  }
				  
			  setMap(new char[getN()][getM()]);
			 
			  int i = 0, j = 0;
			  String line;
			  while ((line = br.readLine()) != null)   {
				  while(line.length() > j) {				  
					  getMap()[i][j] = line.charAt(j++);  				  
				  }
				  j=0;
				  i++;  
			  }
			  
			  in.close();
			  in2.close();
			    }catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
			}
	}
	
	public void printMap(){
		String show = "";
		
		for(int x = 0; x < map.length; x++){
			
			for(int y = 0; y < map[x].length; y++){
				show += map[x][y];
			}
			show += "\n";
		}
		System.out.print(show);
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

public boolean moveObject(int i, int j, int k, int l) {
		
		if(checkValidPosition(i,j,k,l)){
			map[k][l] = map[i][j];
			map[i][j] = ' ';
			return true;
		}
		return false;
		
	}

	private boolean checkValidPosition(int i, int j, int k, int l) {
	
		if(k-i > 1 || l-j > 1)
			return false;
		char thing = map[i][j];
		switch(thing){
		case PLAYER : 
			if(map[k][l]== WALL || map[k][l] == ROCK)
				return false;
			return true;
		case ROCK : 
			if(map[k][l] == EMPTY)
				return true;
			return false;
		}
		return false;
	}

	public void pickUpDiamond(Player p, int m, int n) {
		if(map[m][n] == DIAMOND)
			p.addDiamond();
	}
	
	public void changeTerrain(int x, int y, char to) {
		map[x][y] = to;
	}
}
