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
public class Diamond {
    public static void draw(Graphics2D g, int x, int y) {
        Color c = g.getColor();
        g.setColor(new Color(128,255,255));
        g.fillRect(x, y, 15, 15);
        g.setColor(c);
    }
}
