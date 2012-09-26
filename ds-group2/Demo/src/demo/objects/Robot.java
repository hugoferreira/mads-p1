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
public class Robot implements demo.interfaces.Movable {

    public static void drawNormal(Graphics2D g, int x, int y) {
        Color c = g.getColor();
        g.setColor(new Color(0x0000ff));
        g.fillRect(x, y, 15, 15);
        g.setColor(c);
    }
    
    public static void drawEscavating(Graphics2D g, int x, int y) {
        Color c = g.getColor();
        g.setColor(new Color(0x0000f0));
        g.fillRect(x, y, 15, 15);
        g.setColor(c);
    }

    @Override
    public void move(MoveType m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void waitNow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean validateMove(MoveType m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
