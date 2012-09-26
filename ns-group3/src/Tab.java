import java.awt.Point;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Tab {
	private HashMap<Point, Character> map = new HashMap<Point, Character>();
	private int Xmax;
	private int Ymax;
	public int nDiam;
	public Point robotPos;
	public Point lPos;
	
	public void change(int x, int y, char f){
		Point position = new Point(x, y);
		
		if(f==Constants.ROBOT) {
			robotPos = position;
			if(getPoint(x,y)==Constants.DIAMOND)
				nDiam--;
			//still needs to be tested
			if(getPoint(x,y)==Constants.ROCK){
				if(x > robotPos.x){
					if(((x+1) <= Xmax) && getPoint(x+1,y)==Constants.EMPTY)
						map.put(new Point(x+1,y), '*');
				}
				if(x < robotPos.x){
					if(((x+1) > 1) && getPoint(x-1,y)==Constants.EMPTY)
						map.put(new Point(x-1,y), '*');
				}
			}
				
		}
		map.put(position, f);
	}
	
	public HashMap<Point, Character> getmap(){
		return map;
	}
	
	public char getPoint(int x, int y){
		return map.get(new Point(x,y));
	}
	
	public int getXMax(){
		return Xmax;
	}
	
	public int getYMax(){
		return Ymax;
	}
	
	public void printTab(){
		for(int j=Ymax;j>0;j--){
			for(int i=1; i<=Xmax;i++)
				System.out.print(getPoint(i,j));
			System.out.println();
		}
	}
	
	public void readFromFile(String fileName)
	{
		try{
			// Open the file 
			FileInputStream fstream = new FileInputStream(fileName);


			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			int lineCounter = 0;
			//Read File 
			String tab = "";
			while ((strLine = br.readLine()) != null)   {
				tab += strLine+"\n";
				lineCounter+=1;
			}
			
			Ymax = lineCounter;
			//System.out.println (tab+"\n"+lineCounter);
			String[] lines = tab.split("\n");
			Xmax = lines[0].length();
			for (int i=lineCounter; i> 0; i--)   {
				
				for(int j=1; j<=lines[lineCounter-i].length(); j++)
				{
					if(lines[lineCounter-i].charAt(j-1)==Constants.DIAMOND)
						nDiam++;
					
					if(lines[lineCounter-i].charAt(j-1)==Constants.CLOSED_LIFT)
						lPos=new Point(j,i);
					
					if(lines[lineCounter-i].charAt(j-1)==Constants.ROBOT)
						robotPos=new Point(j,i);
					
					//change(j,i,lines[lineCounter-i].charAt(j-1));
					map.put(new Point(j,i), lines[lineCounter-i].charAt(j-1));
				}
			}
			
			//Close the input stream
			in.close();
			fstream.close();
			
		}catch (Exception e){//Catch exception if any
			System.out.println("Error: " + e.getMessage());
		}
			  
	}
}
