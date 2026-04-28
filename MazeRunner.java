//Does not compile yet
//Still working on plenty of things
//This is just some day one progress
public class MazeRunner{
    private Maze layout;
    private Point entrance;
    private Deque<Stack<Point>> stackStorage;
    private LinkedList<Point> traveled;
    public MazeRunner(String filename) {
        layout = new Maze(filename);
        entrance = layout.getStart();
        stackStorage = new Deque();
        travelled = new LinkedList();
    }
    private boolean travelable(Point p) {
        return layout.getCellValue(p.Y, p.X) == " ";
    }
    private Stack<Point> getTravelables(Point p) {
        Stack newStack = new Stack();
        Point step = p.clone();
        step.move(1, 0); //center to east
        if (travelable(step) && !traveled.contains(step)) {
            newStack.push(step.clone());
        }
        step.move(-1, 1); //east to north
        if (travelable(step) && !traveled.contains(step)) {
            newStack.push(step.clone());
        }
        step.move(-1, -1); //north to west
        if (travelable(step) && !traveled.contains(step)) {
            newStack.push(step.clone());
        }
    }

    public runMaze() {
        boolean foundExit = false;
        Stack initialPoint = new Stack();
        initialPoint.push(entrance);
        stackStorage.push(initialPoint);
        while (!foundExit) {
            if (stackStorage.size() == 0) {
                if (travelled.size() == 0) {
                    System.out.println("Something weird happened...");
                } else {
                    System.out.print("Despite our best efforts, this");
                    System.out.print(" maze appears to be unsolvable.");
                }
                System.exit();

