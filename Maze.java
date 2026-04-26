/**
 * Models a simple maze.
 */
public class Maze {

    /**
     * Create a new maze based upon the file defaultMaze.txt.
     */
    public Maze() {
        // DO
    }

    /**
     * Create a new maze based on input from a file.
     * @param fileName the file that serves as the input
     */
    public Maze(String fileName) {
        // DO
    }

    /**
     * Create a maze object from an already existing
     * collection of MazeCells.
     * @param maze The already existing maze
     */
    public Maze(MazeCell[][] maze) {
        // DO
    }

    /**
     * Find the symbol at the specified location in the
     * maze
     * @param row The row of the cell value you want
     * @param column The column of the cell value you want
     * @return MazeCell enum of the type of cell it is
     */
    public MazeCell getCellValue(int row, int column) {
        // DO
    }

    /**
     * Get the total number of columns in the maze.
     */
    public int getNumCols() {
        // DO
    }

    /**
     * Get the total number of rows in the maze.
     */
    public int getNumRows() {
        // DO
    }

    /**
     * Return the starting location of the maze.
     */
    public Point getStart() {
        // DO
    }

    /**
     * Determine if the Point value provided maps to an
     * exit location.
     * @param location The point that is being determined
     * @return true if the point is an exit
     */
    public isExit(Point location) {
        // DO
    }

    /**
     * Sets a location in the maze to a specified value.
     * @param row The row of the desired location
     * @param column Thw column of the desired location
     * @param value The value of the desired location
     */
    public void setCellValue(int row, int column, MazeCell value) {
        // DO
    }

    /**
     * Mark a discovered path within the maze.
     * @param path the Path being marked
     */
    public void setPath(Deque<Point> path) {
        // DO
    }

    /**
     * Create a String representing the current state of the maze.
     */
    public String toString() {
        // DO
    }
}
