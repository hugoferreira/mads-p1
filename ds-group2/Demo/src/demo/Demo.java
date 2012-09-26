/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rúben Veloso
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Map m = new Map();
        m.loadMap("example1.map");
        m.printmsp();
    }
}
