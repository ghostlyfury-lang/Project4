mport java.awt.Point;
import java.util.LinkedList;
import java.util.Stack;
import java.io.FileNotFoundException;
/**
 * Models a maze runner to construct and solve a maze for a given
 * file name
 * @author Luca Rizzo, Jonathan Seda
 * @version May, 2026
 */
public class MazeRunner{
    /** the layout of the maze */
    private Maze layout;
    /** the location of the maze entrance */
    private Point entrance;
    /** a storage container for several stacks traveling through
     * the maze */
    private LinkedList<Stack<Point>> stackStorage;
    /** a storage container for the final path of the maze */
    private LinkedList<Point> finalPath = new LinkedList<Point>();

    /**
     * Default constructor for a maze runner
     * Uses the file "defaultMaze.txt"
     * @throws InvalidInitException if there is an issue
     * in the maze construction
     * @throws FileNotFoundException if there is no
     * file with the name "defaultMaze.txt"
     */
    public MazeRunner() throws InvalidInitException,
                FileNotFoundException {
        this("defaultMaze.txt");
    }



    /**
     * A constructor for the MazeRunner based on a file name
     * @param filename the file in which the maze is found
     * @throws InvalidInitException if there is a problem
     * constructing the maze
     * @throws FileNotFoundException if there is no
     * file with the given name
     */
    public MazeRunner(String filename) throws InvalidInitException,
                    FileNotFoundException {
        layout = new Maze(filename);
        entrance = layout.getStart();
        stackStorage = new LinkedList<Stack<Point>>();
    }

    /**
     * helper function to determine if a spot in the maze
     * can be traveled to
     * @param p the point in the maze
         * @return a boolean determining whether it can be traeled to or not
     */
    private boolean travelable(Point p) {
        MazeCell check = layout.getCellValue((int) p.getY(), (int) p.getX());
        return check.equals(MazeCell.OPEN) || check.equals(MazeCell.EXIT);
    }

    @SuppressWarnings("unchecked")
    /**
     * creates and stores a new stack in the stackStorage if
     * a given point can be validly traveled to
     * @param s the stack to check, add, and store
     * @param p the point to check for valid movement
     */
    private void storeIfPushable(Stack<Point> s, Point p) {
        if (travelable(p) && !s.contains(p)) {
            Stack<Point> copy = (Stack<Point>) s.clone();
            copy.push(p);
            stackStorage.add(copy);
        }
    }

    /**
     * generates the stacks for the next travelable locations based on
     * the location of a given stack and stores them in stackStorage
     * @param s the stack to check for the next movements
     */
    private void createTravelables(Stack<Point> s) {
        Point step = s.peek();
        Point east = (Point) step.clone();
        Point north = (Point) step.clone();
        Point west = (Point) step.clone();
        Point south = (Point) step.clone();
        east.translate(1, 0);
        north.translate(0, 1);
        west.translate(-1, 0);
        south.translate(0, -1);
        storeIfPushable(s, east);
        storeIfPushable(s, north);
        storeIfPushable(s, west);
        storeIfPushable(s, south);
    }

    /**
     * Generates solves, and outputs a maze solution based on the
     * maze within the file to be searched
     */
    public void runMaze() {
        boolean foundExit = false;
        Stack<Point> initialPoint = new Stack<Point>();
        initialPoint.push(entrance);
        stackStorage.push(initialPoint);
        while (!foundExit) {
            if (stackStorage.size() == 0) {
                System.out.print("Despite our best efforts, this");
                System.out.println(" maze appears to be unsolvable.");
                                System.exit(1);
            }
            Stack<Point> nextStack = stackStorage.remove();
            if (layout.isExit(nextStack.peek())) {
                foundExit = true;
                int iterations = nextStack.size();
                for (int i = 0; i < iterations; i++) {
                    Point p = nextStack.pop();
                    finalPath.add(p);
                }
            } else {
                createTravelables(nextStack);
            }
        }

        layout.setPath(finalPath);
        System.out.println(layout.toString());
    }

    /**
     * returns a string-based representation of the maze
     * and its solution
     * @return the maze and its solution
     */
    public String toString() {
        return layout.toString();
    }
}
