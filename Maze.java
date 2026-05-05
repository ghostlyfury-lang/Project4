import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Deque;
import java.awt.Point;
import java.io.File;
/**
 * Models a simple maze.
 */
public class Maze {
    /** the number of rows in the maze */
    private int rows;
    /** the number of columns in the maze */
    private int cols;

    /** the row of the maze entrance */
    private int enterRow;
    /** the column of the maze entrance */
    private int enterCol;

    /** the grid representation of the maze */
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
     * Create a new maze based upon a given grid of MazeCells.
     * @param maze the given grid of cells
     * @throws InvalidInitException if there is a problem in the maze
     * construction
     */
    public Maze(MazeCell[][] maze) throws InvalidInitException {
        // Complete a maze from a 2d Array of MazeCell objects
        // NOT a copy
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.grid = new MazeCell[this.rows][this.cols]; // creates our maze
        this.enterRow = - 1;
        this.enterCol = - 1;

        for (int r = 0; r < this.rows; r++) { //goes through each row
            for (int c = 0; c < this.cols; c++) { // goes through each col
                this.grid[r][c] = maze[r][c]; // populates our maze
                if (maze[r][c] == MazeCell.ENTER) {
                    if (this.enterRow != - 1) {
                        throw new InvalidInitException("Only 1 entrance");
                    }
                    this.enterRow = r;
                    this.enterCol = c;
                }
            }

        }

        if(this.enterRow == - 1) {
            throw new InvalidInitException("An entrance is required");
        }
    }

    /**
     * Default constructor, generates a maze based on "defaultMaze.txt"
     * @throws InvalidInitException if there is (somehow)
     * an issue in construction
     * @throws FileNotFoundException if there is (somehow) no such file
     * with the name "defaultMaze.txt"
         */
    public Maze() throws InvalidInitException, FileNotFoundException {
        this("defaultMaze.txt");
    }

    /**
     * Create a new maze based on input from a file.
     * @param fileName the file that serves as the input
     * @throws InvalidInitException if there is a problem in the
     * text file used for construction
     * @throws FileNotFoundException if there is no file of the name given
     */
    public Maze(String fileName) throws InvalidInitException,
                FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        this.rows = file.nextInt(); // first # is rows
        this.cols = file.nextInt(); // second # is cols
        file.nextLine(); // moves to the next line

        this.grid = new MazeCell[rows][cols];
        // creates 2d array of type MazeCell of the number of
        // rows and cols found in scanner

        this.enterRow = - 1;
        this.enterCol = - 1; // Initalize these early to check for
        // exception later
        

        for (int r = 0; r < rows; r++) { // goes through each row
            if (!file.hasNextLine()) {
                throw new InvalidInitException("Not enough rows in file: "
                        + (r + 1) + " given, " + rows + " needed");
            } // makes sure the maze has the actual number of rows
              // that it says it does.

            String line = file.nextLine();
            // makes line reference the next line of input

            if(line.length() != cols) {
                throw new InvalidInitException("Not enough columns in file: "
                        + line.length() + " given, " + cols + " needed "
                        + "on row " + (r + 1) + " of the maze");
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
                        throw new InvalidInitException("Only 1 entrance");
                    }
                        // Checks to make sure that the entrance hasn't
                        // already been found before assigning it. If it
                        // has throw the error.

                    this.enterRow = r;
                    this.enterCol = c;
                }
            }
        }
        if (this.enterRow == -1) {
            throw new InvalidInitException("An entrance is required");
        }
        file.close();
    }


    /**
     * Find the symbol at the specified location in the
         * maze
     * @param row The row of the cell value you want
     * @param column The column of the cell value you want
     * @return The enum of the type of cell it is
     */
    public MazeCell getCellValue(int row, int column) {
        MazeCell returnable = MazeCell.INVALID;
        if (0 <= row && row < rows && 0 <= column && column < cols) {
            returnable = grid[row][column];
        }
        return returnable;
    }

    /**
     * Get the total number of columns in the maze.
     * @return the number of columns
     */
    public int getNumCols() {
        return cols;
    }

    /**
     * Get the total number of rows in the maze.
     * @return the number of rows
     */
    public int getNumRows() {
        return rows;
    }
        
    /**
     * Return the starting location of the maze.
     * @return an ordered pair (Point) of where the start is
     */
    public Point getStart() {
        return new Point(enterCol, enterRow);
    }

    /**
     * Determine if the Point value provided maps to an
     * exit location.
     * @param location The point that is being determined
     * @return true if the point is an exit
     */
    public boolean isExit(Point location) {
        int x = (int) location.getX();
        int y = (int) location.getY();
        return getCellValue(y, x).equals(MazeCell.EXIT);
    }

    /**
     * Sets a location in the maze to a specified value.
     * @param row The row of the desired location
     * @param column The column of the desired location
     * @param value The intended value of the desired location
     */
        public void setCellValue(int row, int column, MazeCell value) {
        grid[row][column] = value;
    }

    /**
     * Mark a discovered path within the maze.
     * @param path the Path being marked
     */
    //
    // Takes a Deque (deck) that contains Point objects called path
    // The path is represented by a deque of points, where each point
    // contains an x and y coordinate.
    // The method is meant to take that path and store it as the path from
    // the entrance to the exit, while also expressing the coordinates of a
    // point as locations on the grid. i.e Point(x,y) becomes grid(y, x)
    public void setPath(Deque<Point> path) {
        int iterations = path.size();
        for (int i = 0; i < iterations; i++) {
            Point p = path.remove();
            grid[p.y][p.x] = MazeCell.PERSON;
        }
    }

    /**
     * Create a String representing the current state of the maze.
     * @return a string-based representation of the maze
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
                        for (int r = 0; r < rows; r++) { // increment through rows
                for (int c = 0; c < cols; c++) { // increment through columns
                    sb.append(toChar(grid[r][c]));
                }
                sb.append("\n"); // new line when each row is finished printing
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Something went wrong within " +
                    "the toString method of class Maze:");
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    /**
     * helper method to convert the characters of our file to their
     * respective MazeCell enum value
     * @param ch The character that is being converted
     * @throws InvalidInitException if character is invalid
     * @return the MazeCell value
     */
    private MazeCell toCell (char ch) {
        MazeCell ret = MazeCell.INVALID;

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
        }
        return ret;
    }

    /**
     * helper method to convert MazeCell enum value to character,
     * Mainly using so the toString return value is prettier
     * @param cell The cell were converting to the character
     * @return char The character we got from converting
     */
    public char toChar(MazeCell cell) throws IllegalArgumentException{
        if (cell == null) {throw new IllegalArgumentException(
                    "cells cannot be null");}
        char ret = '?';
        switch (cell) {
            case WALL:
                ret = 'W';
                break;

            case ENTER:
                ret = 'E';
                break;

            case EXIT:
                ret = 'O';
                break;

            case OPEN:
                ret = ' ';
                break;

            case PERSON:
                ret = '*';
                break;
                
            default:
                ret = '?';
                break;
        }
        return ret;

    }
}
