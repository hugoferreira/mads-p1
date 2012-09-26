package console;

import java.util.Scanner;
import main.Map;

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
