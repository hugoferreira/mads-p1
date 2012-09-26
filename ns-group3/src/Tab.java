import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class Tab {

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
			System.out.println (tab);
			String[] lines = tab.split("\n");
			
			for (int i=lineCounter; i!= 0; i--)   {
				
				for(int j=1; j!=lineCounter+1; j++)
				{
					//map.change(j,i,lines[lineCounter-i].charAt(j));
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
