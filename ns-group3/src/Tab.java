import java.awt.Point;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Tab {
	private HashMap<Point, Character> map = new HashMap<Point, Character>();
	
	public void change(int x, int y, char f){
		Point position = new Point(x, y);
		map.put(position, f);
	}
	
	public HashMap<Point, Character> getmap(){
		return map;
	}
	
	public void printTab(){
		for(int i=1; i<=6;i++){
			for(int j=6;j>0;j--)
				System.out.print(map.get(new Point(i,j)));
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
			//System.out.println (tab+"\n"+lineCounter);
			String[] lines = tab.split("\n");
			
			for (int i=6; i> 0; i--)   {
				
				for(int j=1; j<=6; j++)
				{
					change(j,i,lines[lineCounter-i].charAt(j-1));
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
