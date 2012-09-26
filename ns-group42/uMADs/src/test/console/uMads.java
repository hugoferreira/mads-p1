package test.console;

import java.util.Scanner;
import main.Map;
import main.Pos;

/**
 *
 * @author ei10139
 */
public class uMads {
    public Map map = new Map();
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
            loadMap(in.nextLine());
        }
        catch(Exception e)
        {
            System.out.println("Invalid file.");
            return;
        }
        
        while(true)
        {
            System.out.println("Action: ");
            analyze(in.nextLine().charAt(0));
        }
    }
    
    public void rockcheck(Map m){
    	for (int i = 0; i<m.map.length; i++)
    	{
    		for (int j = 0; j<m.map.length; j++)
    		{
    			if(m.map[i][j]==Pos.ROCK)
    				if(belowrockcheck(m,i,j)==Pos.EMPTY)
    					droprock(m,i,j);
    				else if(belowrockcheck(m,i,j)==Pos.ROCK)
    					sliderock(m,i,j);
    		}
    	}
    }
    
    private void sliderock(Map m, int i, int j) {
    	if( m.map[i+1][j+1]==Pos.EMPTY){
		m.map[i+1][j+1]=Pos.ROCK;
		m.map[i][j]=Pos.EMPTY;
    	}
    	else
    		if( m.map[i-1][j+1]==Pos.EMPTY){
    			m.map[i-1][j+1]=Pos.ROCK;
    			m.map[i][j]=Pos.EMPTY;
    		}
		
	}

	private void droprock(Map m, int i, int j) {
		m.map[i][j]=Pos.EMPTY;
		m.map[i][j+1]=Pos.ROCK;
		
	}

	public  Pos belowrockcheck(Map m, int i, int j) {
    	
    		return m.map[i][j+1];
    	
    	


		
	}

	public void loadMap(String file)
    {
        // TODO
    }
    
    public void analyze(char input)
    {
        switch(input)
        {
        case 'L':
            break;
        case 'R':
            break;
        case 'U':
            break;
        case 'D':
            break;
        case 'W':
            break;
        default:
            break;
        }
    }
}
