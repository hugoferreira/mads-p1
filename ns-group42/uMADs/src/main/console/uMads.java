package main.console;

import java.util.Scanner;

import main.Map;
import main.MapTimeHandler;
import main.Pos;

/**
 *
 * @author ei10139
 */
public class uMads {
    public Map map = new Map();
    public MapTimeHandler mth = new MapTimeHandler();
    Map prev = null;
    
    Scanner in = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        System.out.println("uMads 2012 Pro");
        new uMads().run();
    }
    
    
    public void run()
    {
        try
        {
            System.out.println("Read from: ");
            String file = in.nextLine().trim();
            
            map = new Map( file.length() == 0 ? "res/map1.txt" : file);
        }
        catch(Exception e)
        {
            System.out.println("Invalid file.");
            return;
        }
        
        print();
        
        while(true)
        {
        	mth.storeMap(map.clone());
            System.out.println("Action: ");
            analyze(in.nextLine().charAt(0));
            rockcheck();
            
            print();
        }
    }
    
    public void print(){
    for(Pos[] line : map.map){
    for(Pos p : line){
    System.out.print(convert(p));
    }
    System.out.println();
    }
    }
    
    public String convert(Pos p){
    switch (p) {
		case EARTH:
		return ".";
		case ROBOT:
		return "R";
		case LIFT_O:
		return "O";
		case LIFT_C:
		return "C";
		case WALL:
		return "#";
		case DIAMOND:
		return "x";
		case EMPTY:
		return " ";
		case ROCK:
		return "*";
		default:
		break;
		}
		return "?";
    }
    
    public void rockcheck(){
    for (int i=(map.map.length) -1; i>=0; i--)
    {
	    for (int j = map.map.length-1; j>=0; j--)
	    {
	    if(map.map[i][j]==Pos.ROCK){
	    	prev = mth.past.get(mth.past.size()-1);
		    if(belowrockcheck(map,i,j)==Pos.EMPTY)
		    	droprock(map,i,j);
		    else if(belowrockcheck(map,i,j)==Pos.ROCK || belowrockcheck(map,i,j)==Pos.DIAMOND)
		    	sliderock(map,i,j);
		    else if(belowrockcheck(map,i,j)==Pos.ROBOT)
		    	kill(i,j);
		    }
	    }
	    }
    }
    
    private void sliderock(Map m, int i, int j) {
    if( m.map[i+1][j+1]==Pos.EMPTY || prev!=null && (m.map[i+1][j+1]==Pos.ROCK && prev.map[i+1][j+1]==Pos.EMPTY)){
		m.map[i+1][j+1]=Pos.ROCK;
		m.map[i][j]=Pos.EMPTY;
    }
    else if( m.map[i+1][j-1]==Pos.EMPTY || prev!=null && (m.map[i+1][j-1]==Pos.ROCK && prev.map[i+1][j-1]==Pos.EMPTY)){
	    m.map[i+1][j-1]=Pos.ROCK;
	    m.map[i][j]=Pos.EMPTY;
    }
}

private void droprock(Map m, int i, int j) {
m.map[i][j]=Pos.EMPTY;
m.map[i+1][j]=Pos.ROCK;
}

public void kill(int i, int j){	
	System.out.println("GAME OVER!!!! \n");

	print();
	
	System.exit(0);
}

public  Pos belowrockcheck(Map m, int i, int j) {
   
    return m.map[i+1][j];
}
    
    public void analyze(char input)
    {
    input = Character.toUpperCase(input);
    System.out.println(input);
   
        switch(input)
        {
        case 'W':
        move(0,-1);
            break;
        case 'S':
        move(0,1);
            break;
        case 'A':
        move(-1,0);
            break;
        case 'D':
        move(1,0);
            break;
        case 'F':
        default:
            break;
        }
    }
private void move(int xs, int ys){
	int x=map.getRobotX(), y= map.getRobotY();
	if (map.map[y+ys][x+xs] == Pos.EMPTY
	 || map.map[y+ys][x+xs] == Pos.EARTH
	 || map.map[y+ys][x+xs] == Pos.DIAMOND)
		{
		map.map[y+ys][x+xs] = Pos.ROBOT;
		map.map[y][x] = Pos.EMPTY;
		}
	else if (map.map[y+ys][x+xs] == Pos.ROCK && map.map[y+ys+ys][x+xs+xs] == Pos.EMPTY)
		{
		map.map[y+ys+ys][x+xs+xs] = Pos.ROCK;
		map.map[y+ys][x+xs] = Pos.ROBOT;
		map.map[y][x] = Pos.EMPTY;
	}
}
}