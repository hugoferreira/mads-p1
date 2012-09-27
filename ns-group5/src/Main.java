import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static Mine mine = new Mine(new File("example1.map"));
    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList<String> history = new ArrayList<String>();
    public static int actual_map = 0;

    /**
     * @param args
     */
    public static void main(String[] args) {
            boolean invalid = false;

            // add first tab to history
            history.add(mine.toString());

            // MAIN CICLE
            while(true) {

                    // exit condition

                    // show map
                    mine.print();

                    // ask user for a command
                    System.out.print("Input: ");
                    String command = scanner.nextLine();
                    if (!(command.toLowerCase().charAt(0) == 'w'))
                            if (!mine.move(command.charAt(0)))
                                    if(!undoRedo(command)){
                                            invalid = true;
                                            System.out.println("Invalid Command");
                                    }
                                    else {
                                            invalid = true;
                                    }

                    if(!invalid){
                            undoRedoAddMapHistory();

                            // update map
                            mine.updateMap();

                            mine.levelUp();
                    }
                    invalid = false;
            }

    }

    // perform an undo or a redo command
    public static boolean undoRedo(String command) {

            if(command.toLowerCase().charAt(0) == 'z') {
                    if(!(actual_map == 0)) {
                            actual_map--;
                            mine = new Mine(history.get(actual_map));
                    }
                    return true;
            }

            if(command.toLowerCase().charAt(0) == 'y') {
                    if(!(actual_map == (history.size()-1))) {
                            actual_map++;
                            mine = new Mine(history.get(actual_map));
                    }
                    return true;
            }

            return false;
    }

    public static void undoRedoAddMapHistory() {

            System.out.println("Added to history");

            if (actual_map == (history.size()-1)) {
                    history.add(mine.toString());
                    actual_map++;
            }
            else {
                    actual_map++;
                    history.remove(actual_map);
                    history.add(actual_map, mine.toString());
            }
    }

}