/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Rúben Veloso
 */
public class Map {
    public int nDiamons = 0;
    public char[][] map = null;
    public char[][] lastMap = null;
    
    public void loadMap(String file) throws FileNotFoundException, IOException
    {
         FileInputStream fstream = new FileInputStream(file);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        
        int cols = 0;
        ArrayList<String> lines = new ArrayList<String>();
        while((strLine = br.readLine()) != null){
            lines.add(strLine);
            cols = strLine.length();
        }
        map = new char[lines.size()][cols];
        lastMap = new char[lines.size()][cols];
        for (int i = 0; i < map.length; i++) {
            strLine = lines.get(i);
            for (int j = 0; j < cols; j++) {
                map[i][j] = strLine.charAt(j);
                if(map[i][j] == '*'){
                    nDiamons++;
                }
            }
        }
        nDiamons++;
        
    }
    
    public void printmsp(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println("");
        }
    }
}
