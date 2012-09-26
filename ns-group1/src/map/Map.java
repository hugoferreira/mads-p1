package map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Map {
	
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
			  
			  int temp;
			  int i = 0, j = 0;
			  while ((temp = br.read()) != -1)   {
				  if( temp == 13){
					  br.read();
					j=0;
					i++;
				  }
				  else
					  getMap()[i][j++] = (char) temp;
			  }
			  
			  in.close();
			  in2.close();
			    }catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
			}
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

	public void moveObject(int i, int j, int k, int l) {
		
		if(checkValidPosition(i,j,k,l)){
			map[k][l] = map[i][j];
			map[i][j] = ' ';
		}
		
	}

	private boolean checkValidPosition(int i, int j, int k, int l) {
	
		if(k-i != 1 || l-j != 1)
			return false;
		char thing = map[i][j];
		switch(thing){
		case 'R' : 
			if(map[k][l]== '#' || map[k][l] == '*')
				return false;
			return true;
		case '*' : 
			if(map[k][l] == ' ')
				return true;
			return false;
		}
		return false;
	}
	
	public void pickUpDiamond(Player p, int m, int n) {
		if(map[m][n] == DIAMOND)
			p.addDiamond();
	}
}
