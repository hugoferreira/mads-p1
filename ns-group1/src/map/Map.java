package map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Map {
	
	
	private static final char PLAYER = 'R';
	private static final char ROCK = '*';
	private static final char WALL = '#';
	private static final char EMPTY = ' ';
	private static final char LIFT = 'L';
	private int n;
	private int m;
	private char[][] map;
	private Pair<Integer, Integer> lift;
	private char DIAMOND = 'x';
	private ArrayList<Pair> visitedRocks;
	public Stack<Pair<Map, Player>> back = new Stack<Pair<Map, Player>>();
	public Stack<Pair<Map, Player>> forward = new Stack<Pair<Map, Player>>();
	
	public Map(Map oldMap){
		
		this.back = (Stack<Pair<Map, Player>>) oldMap.back.clone();
		this.forward = (Stack<Pair<Map, Player>>) oldMap.forward.clone();
		
		this.lift = oldMap.lift;
		this.m = oldMap.getM();
		this.n = oldMap.getN();
		this.map = new char[n][m];
		for(int i = 0; i<n; i++)
			for(int j=0;j<m;j++)
				map[i][j]=oldMap.getMap()[i][j];
				
	
		
		
	}
	
	public Map() {
		// TODO Auto-generated constructor stub
	}

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
					  if (line.charAt(j) == LIFT)
							  lift = new Pair<Integer, Integer>(i,j);
					  
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
			if(map[i][j] == ROCK) {
				Pair<Integer, Integer> p = new Pair<Integer, Integer>(k,l);
				visitedRocks.add(p);
			}
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
	
	public void checkRocks() {
		visitedRocks = new ArrayList<Pair>();
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == ROCK) {
					Pair<Integer, Integer> checkPair = new Pair<Integer, Integer>(i,j);
					if(!visitedRocks.contains(checkPair))
						processRock(i, j);
				}
			}
		}
	}

	private void processRock(int y, int x) {
		if(map[y+1][x] == EMPTY)
			moveObject(y, x, y+1, x);
		else if(map[y+1][x] == ROCK) {
			if(map[y+1][x+1] == EMPTY)
				moveObject(y, x, y+1, x+1);
			else if(map[y+1][x-1] == EMPTY)
				moveObject(y, x, y+1, x-1);
		}
		else if(map[y+1][x] == PLAYER)
			map[y+1][x] = 'd';
	}
	
	public Pair<Integer, Integer> getPlayer() {
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == PLAYER)
					return new Pair<Integer, Integer>(i,j);
			}
		}
		return null;
	}
}
