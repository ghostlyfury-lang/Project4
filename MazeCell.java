/**
 * Characters needed for drawing a Maze.
 * @author Luca Rizzo, Jonathan Seda
 * @version May, 2026
 */
public enum MazeCell {
    /** Entrance to the maze */
    ENTER("Enter"),
    /** Exit of the maze */
    EXIT("Exit"),
    /** Not a valid function within the maze */
    INVALID("Invalid"),
    /** Walkway passage of the maze */
    OPEN("Open"),
    /** Marks a path to an exit */
    PERSON("Person"),
    /** Cannot be passed through in the maze */
    WALL("Wall");

    /**
     * The name or text associated with a MazeCell
     */
    private String text;

    /**
     * Default constructor for a MazeCell enum
     * @param text the text associated with the enum
     */
    private MazeCell(String text) {
                this.text = text;
    }
    /**
     * Converts the text of a MazeCell into a printable string
     * @return the string form of the MazeCell
     */
    public String toString() {
        return text;
    }
}
