/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.objects;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Rúben Veloso
 */
public class Wall{
    public static void draw(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, 15, 15);
        g.setColor(Color.LIGHT_GRAY);
    }
    
}
