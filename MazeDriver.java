import java.io.FileNotFoundException;
public class MazeDriver {
    public MazeDriver() {}

    public static void main(String[] args) {
        MazeRunner newRunner = null;
        try {
            newRunner = new MazeRunner("minimaze.txt");
            newRunner.runMaze();
        } catch (InvalidInitException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
