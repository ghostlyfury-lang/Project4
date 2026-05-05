import java.awt.Point;
import java.util.LinkedList;
import java.util.Stack;
import java.io.FileNotFoundException;
public class MazeRunner{
    private Maze layout;
    private Point entrance;
    private LinkedList<Stack<Point>> stackStorage;
    private int nodesChecked = 0;
    public MazeRunner(String filename) throws InvalidInitException,
                    FileNotFoundException {
        layout = new Maze(filename);
        entrance = layout.getStart();
        stackStorage = new LinkedList<Stack<Point>>();
    }
    private boolean travelable(Point p) {
        MazeCell check = layout.getCellValue((int) p.getY(), (int) p.getX());
        return check.equals(MazeCell.OPEN) || check.equals(MazeCell.EXIT);
    }
    @SuppressWarnings("unchecked")
    private void storeIfPushable(Stack<Point> s, Point p) {
        if (travelable(p) && !s.contains(p)) {
            Stack<Point> copy = (Stack<Point>) s.clone();
            copy.push(p);
            stackStorage.add(copy);
            nodesChecked++;
        }
    }
    private void createTravelables(Stack<Point> s) {
                Point step = s.peek();
        Point east = (Point) step.clone();
        Point north = (Point) step.clone();
        Point west = (Point) step.clone();
        Point south = (Point) step.clone();
        east.translate(1, 0);
        north.translate(0, 1); //technically because of the way the
        west.translate(-1, 0); //grid is set up, south is actually
        south.translate(0, -1); //north and north is actually south
        storeIfPushable(s, east);
        storeIfPushable(s, north);
        storeIfPushable(s, west);
        storeIfPushable(s, south);
    }

    public void runMaze() {
        boolean foundExit = false;
        Stack<Point> exitPath = new Stack<Point>();
        Stack<Point> initialPoint = new Stack<Point>();
        initialPoint.push(entrance);
        stackStorage.push(initialPoint);
        nodesChecked++;
        Stack<Point> debugStack = new Stack<Point>();
        while (!foundExit) {
            if (stackStorage.size() == 0) {
                                System.out.print("Despite our best efforts, this");
                System.out.println(" maze appears to be unsolvable.");
                System.out.println(nodesChecked + " nodes checked");
                while (debugStack.size() != 0) {
                    System.out.println(debugStack.pop().toString());
                }
                System.exit(1);
            }
            Stack<Point> nextStack = stackStorage.remove();
            debugStack = nextStack;
            if (layout.isExit(nextStack.peek())) {
                foundExit = true;
                exitPath = nextStack;
            } else {
                createTravelables(nextStack);
            }
        }
        Stack<Point> reversal = new Stack<Point>();
        for (int i = 0; i < exitPath.size(); i++) {
            reversal.push(exitPath.pop());
        }
        String path = "";
        for (int i = 0; i < reversal.size(); i++) {
            Point p = reversal.pop();
            path += p.toString();
            path += "---";
        }
    }
}
