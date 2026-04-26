/**
 * Simple exception class to inform us when the things go wrong during
 * initializition.
 */
public class InvalidInitException extends Exception {

    /**
     * Constructor that creates an exception object
     * and takes a message as an argument
     * @param message The message you want to output
     */
    public InvalidInitException(String message) {
        super(message);
    }
    /**
     * Default constructor with no arguments that
     * created an exception object and outputs a
     * default message
     */
    public InvalidInitException() {
        super("Invalid maze initialization");
    }
}
