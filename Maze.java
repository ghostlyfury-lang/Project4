/**
 * Models a simple maze.
 */
public class Maze {
    private int rows;
    private int cols;

    private int enterRow;
    private int enterCol;
    private int exitRow;
    private int exitCol;

    private MazeCell[][] grid;

// The function of the maze constructors
//
// File Constructor: creates the maze from reading a file
//
// 1. Uses a scanner to read through the file
//
// 2. Allocated the 2d Array
//      Meaning create the array structure without actually filling it out
//
// 3. Converts characters into MazeCell enums
//      Is this done before populating the array?
//      No, it's done as you populate the array.
//      You read a character, convert it, and immediately store the enum
//      into the array. That way the character W becomes the enum wall.
//      While W does become an enum it is still used to represent the maze
//
// 4. Tracks entrance and exit
//      The same deal as converting characters into an enum, except we
//      keep track of where this enum value is in the maze.
//
// 5. Validate the maze
//      Meaning just make sure there are no errors
//
// 6. Throws our exception class if anything is wrong (maze is unvalidated)



    /**
     * Create a new maze based upon the file defaultMaze.txt.
     */public Maze(MazeCell[][] maze) {
        // Complete a maze from a 2d Array of MazeCell objects
        // NOT a copy
        this.rows = maze.length;
        this.cols = maze[0] length;
        this.grid = new MazeCell[this.rows][this.cols]; // creates our maze structure
        this.enterRow = - 1;
        this.enterCol = - 1;
        this.exitRow = - 1;
        this.exitCol = - 1;

        for (int r = 0; r < this.rows; r++) { //goes through each row
            for (int c = 0; c < this.cols; c++) { // goes through each col
                this.grid[r][c] = maze[r][c]; // populates our maze
                if (maze[r][c] == MazeCell.ENTER) {
                    if (this.enterRow != - 1) {
                        throw new InvalidInitException("No more than 1 entrance");
                    }
                    this.enterRow = r;
                    this.enterCol = c;
                }

                if (maze[r][c] == MazeCell.EXIT) {
                    if (this.exitRow != - 1) {
                        throw new InvalidInitException("No more than 1 entrance");
                    }
                    this.exitRow = r;
                    this.exitCol = c;
                }
            }

        }

        if(this.enterRow == - 1 || this.exitRow == -1) {
            throw new InvalidInitException("Only 1 entrance and exit allowed");
        }
    }
    public Maze() {
        this("defaultMaze.txt");
    }

    /**
     * Create a new maze based on input from a file.
     * @param fileName the file that serves as the input
     */
    public Maze(String fileName) {
        Scanner file = new Scanner(new File(fileName));
        this.rows = file.nextInt(); // first # is rows
        this.cols = file.nextInt(); // second # is cols
        file.nextLine(); // moves to the next line

        this.grid = new MazeCell[rows][cols];
        // creates 2d array of type MazeCell of the number of
        // rows and cols found in scanner

        this.enterRow = - 1;
        this.enterCol = - 1;
        this.exitRow = - 1;
        this.exitCol = - 1; // Initalize these early to check for
        // exception later


        for (int r = 0; r < rows; r++) { // goes through each row
            if (!file.hasNextLine()) {
                throw new InvalidInitException();
            } // makes sure the maze has the actual number of rows
              // that it says it does.

            String line = file.nextLine();
            // makes line reference the next line of input

            if(line.length() != cols) {
                throw new InvalidInitException();
            } // makes sure that the length of the line has the
              // actual number of cols that the input says it
              // does

            for (int c = 0; c < cols; c++) {
                char ch = line.charAt(c); // stored character in column
                MazeCell cell = toCell(ch);
                // converts character to respective MazeCell enum
                grid[r][c] = cell; // stores our enum in the array


                if (cell == MazeCell.ENTER) {
                    if(this.enterRow != -1) {
                        throw new InvalidInitException("No more than 1 entrance");
                    }
                        // Checks to make sure that the entrance hasn't
                        // already been found before assigning it. If it
                        // has throw the error. Same logic for exit.

                    this.enterRow = r;
                    this.enterCol = c;
                }

                if (cell == MazeCell.EXIT) {
                    if(this.exitRow != -1) {
                        throw new InvalidInitException("No more than 1 exit");
                    }
                    this.exitRow = r;
                    this.exitCol = c;
                }
            }
        }
        if (this.enterRow == -1 || this.exitRow == -1) {
            throw new InvalidInitException("No entrance or exit found");
        }
    }

    /**
     * Create a maze object from an already existing
     * collection of MazeCells.
     * @param maze The already existing maze
     */
    public Maze(MazeCell[][] maze) {
        // Complete a maze from a 2d Array of MazeCell objects
        // NOT a copy
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.grid = new MazeCell[this.rows][this.cols]; // creates our maze structure
        this.enterRow = - 1;
        this.enterCol = - 1;
        this.exitRow = - 1;
        this.exitCol = - 1;

        for (int r = 0; r < this.rows; r++) { //goes through each row
            for (int c = 0; c < this.cols; c++) { // goes through each col
                this.grid[r][c] = maze[r][c]; // populates our maze
                if (maze[r][c] == MazeCell.ENTER) {
                    if (this.enterRow != - 1) {
                        throw new InvalidInitException("No more than 1 entrance");
                    }
                    this.enterRow = r;
                    this.enterCol = c;
                }

                if (maze[r][c] == MazeCell.EXIT) {
                    if (this.exitRow != - 1) {
                        throw new InvalidInitException("No more than 1 exit");
                    }
                    this.exitRow = r;
                    this.exitCol = c;
                }
            }
        }

        if(this.enterRow == - 1 || this.exitRow == -1) {
            throw new InvalidInitException("No entrance or exit found");
        }
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
    public boolean isExit(Point location) {
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

    /**
     * helper method to convert the characters of our file to their
     * respective MazeCell enum value
     * @param ch The character that is being converted
     * @throws InvalidInitException if character is invalid
     * @return the MazeCell value
     */
    private MazeCell toCell (char ch) throws InvalidInitException {
        MazeCell ret = null;

        switch (ch) {
            case 'W':
                ret = MazeCell.WALL;
                break;

            case 'E':
                ret = MazeCell.ENTER;
                break;

            case 'X':
                ret = MazeCell.EXIT;
                break;

            case ' ':
                ret = MazeCell.OPEN;
                break;

            case 'P':
                ret = MazeCell.PERSON;
                break;

            default;
                break;
        }
        if (ret == null) {
            throw new InvalidInitException("Invalid Character");
        }
        return ret;
    }
}
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
