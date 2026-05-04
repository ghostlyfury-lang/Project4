/**
 * Characters needed for drawing a Maze.
 */
public enum MazeCell {
    ENTER("Enter"),
    EXIT("Exit"),
    INVALID("Invalid"),
    OPEN("Open"),
    PERSON("Person"),
    WALL("Wall");

    private String name;
    private MazeCell(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
