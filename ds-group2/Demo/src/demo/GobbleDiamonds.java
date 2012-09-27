package demo;

import demo.interfaces.Movable.MoveType;
import java.util.LinkedList;

/**
 *
 * @author Sofia
 */
public class GobbleDiamonds {

    public GobbleDiamonds() {
    }

    public LinkedList<LinkedList<Integer>> getDiamonds(char[][] map) {
        LinkedList<LinkedList<Integer>> path = new LinkedList<LinkedList<Integer>>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'x') {
                    LinkedList<Integer> coords = new LinkedList<Integer>();
                    coords.push(i);
                    coords.push(j);

                    path.push(coords);
                }
            }
        }

        return path;
    }

    public String lazyPlay(char[][] map, int robotX, int robotY) {
        LinkedList<LinkedList<Integer>> path = getDiamonds(map);
        
        int diamond = -1;
        int distance = -1;
        String solution = "";

        //check which diamond is the chosen one (closer)
        for (int i = 0; i < path.size(); i++) {
            int x = path.get(i).get(0);
            int y = path.get(i).get(1);

            int x_dis = Math.abs(robotX - x);
            int y_dis = Math.abs(robotY - y);

            if (distance < (x_dis + y_dis)) {
                distance = x_dis + y_dis;
                diamond = i;
            }
        }

        LinkedList<LinkedList<Integer>> path_backup = path;
        
        
        //for (int i = 0; i < Math.abs(robotX - path_backup.get(diamond).get(0)); i++) {
         for (int i = 0; i < 100; i++) {  
           
             // no more diamonds to caught
            if (path.size()==0) {
                System.out.println("0 diamonds");
                break;
            }
            
            MoveType currDir = getDir(robotX, robotY, path.get(diamond).get(0), path.get(diamond).get(1));
             //System.out.println("start movement to: " + currDir);
          //  System.out.println("next_spot: " + nextSpot(map, robotX, robotY, currDir));
             
            // movement not allowed - change direction
            while (nextSpot(map, robotX, robotY, currDir) == '*'
                    || nextSpot(map, robotX, robotY, currDir) == '#') {
                currDir = clockDir(currDir);
                
                System.out.println("change here: " + currDir);
            }

            // record direction to take
            switch (currDir) {
                case MOVE_UP:
                    solution += "U";
                    break;
                case MOVE_RIGHT:
                    solution += "R";
                    break;
                case MOVE_DOWN:
                    solution += "D";
                    break;
                case MOVE_LEFT:
                    solution += "L";
                    break;
            }
            
            System.out.println("robotpos: " + robotX + "," + robotY);
            System.out.println("solution: " + solution);
            // update robot position
            LinkedList<Integer> new_coord = nextSpotRobot(map, robotX, robotY, currDir);
            robotX = new_coord.get(0);
            robotY = new_coord.get(1);
            
            // check if diamond caught
            LinkedList<LinkedList<Integer>> caught_diamonds = new LinkedList<LinkedList<Integer>>();
            
            for (int d = 0; d < path.size(); d++) {
                if (robotX == path.get(d).get(0) && robotY == path.get(d).get(1)) {
                    caught_diamonds.push(path.get(d));
                    System.out.println("caught d: " + path.get(d).get(0) + ',' + path.get(d).get(1));
                    
                    if (path.size() == 0) {
                        System.out.println("no more diamonds!");
                        return null;
                    }
                    
                    // check next diamond
                    else if (d == diamond) {
                        System.out.println("d == diamond: " + d);
                        diamond = 0;
                    }
                }
            }

            // delete caught diamonds from list
            for (int caught = 0; caught < caught_diamonds.size(); caught++) {
                path.remove(caught_diamonds.get(caught));
            }
            
            //System.out.println("ending for... " + path_backup.size() + " d: " + diamond);
        }
         System.out.println("return");
        return "no PATH";
    }

    public MoveType getDir(int posX, int posY, int diamondX, int diamondY) {
        MoveType dir = null;

        if (posX < diamondX) {
            dir = MoveType.MOVE_RIGHT;
        } else if (posX > diamondX) {
            dir = MoveType.MOVE_LEFT;
        } else if (posY < diamondY) {
            dir = MoveType.MOVE_DOWN;
        } else {
            dir = MoveType.MOVE_UP;
        }

        return dir;
    }

    public MoveType clockDir(MoveType currDir) {
        MoveType dir = null;

        switch (currDir) {
            case MOVE_UP:
                dir = MoveType.MOVE_RIGHT;
                break;
            case MOVE_RIGHT:
                dir = MoveType.MOVE_DOWN;
                break;
            case MOVE_DOWN:
                dir = MoveType.MOVE_LEFT;
                break;
            case MOVE_LEFT:
                dir = MoveType.MOVE_UP;
                break;
        }

        return dir;
    }

    public char nextSpot(char[][] map, int robotX, int robotY, MoveType currDir) {
        //get next coord
        switch (currDir) {
            case MOVE_UP:
                robotY -= 1;
                break;
            case MOVE_RIGHT:
                robotX += 1;
                break;
            case MOVE_DOWN:
                robotY += 1;
                break;
            case MOVE_LEFT:
                robotX -= 1;
                break;
        }

       // System.out.println("map[" + robotX + "," + robotY + "]: " + map[robotX][robotY]);
        return map[robotY][robotX];
    }
    
    public LinkedList<Integer> nextSpotRobot(char[][] map, int robotX, int robotY, MoveType currDir) {
        //get next coord
        switch (currDir) {
            case MOVE_UP:
                robotY -= 1;
                break;
            case MOVE_RIGHT:
                robotX += 1;
                break;
            case MOVE_DOWN:
                robotY += 1;
                break;
            case MOVE_LEFT:
                robotX -= 1;
                break;
        }

         LinkedList<Integer> new_coord = new LinkedList<Integer>();
         new_coord.push(robotY);
         new_coord.push(robotX);
         
         return new_coord;
    }
}
