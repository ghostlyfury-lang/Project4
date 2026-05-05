import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Drives the process of constructing and solving a maze
 * @author Luca Rizzo, Jonathan Seda
 * @version May, 2026
 */
public class MazeDriver {
    /**
     * The main method for the program of constructing and solving
     * a maze
     * @param args unused
     */
    public static void main(String[] args) {
        //try {
            Scanner input = new Scanner(System.in);
            System.out.println("What is the name of the file with the maze?");
            String fileName = "";
            fileName = input.nextLine();
            input.close();
        //} catch ??
        MazeRunner newRunner = null;
        try {
            newRunner = new MazeRunner(fileName);
            newRunner.runMaze();
        } catch (InvalidInitException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
