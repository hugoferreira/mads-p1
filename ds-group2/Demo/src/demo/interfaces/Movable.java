/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.interfaces;

/**
 *
 * @author Rúben Veloso
 */
public interface Movable {
    
    public static enum MoveType{
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT
    }
    
    public void move(MoveType m);
    public void waitNow();
    
    public boolean validateMove(MoveType m);
    
}
