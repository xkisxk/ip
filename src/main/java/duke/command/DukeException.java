package duke.command;

public class DukeException extends Exception {
    protected String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DukeException() {
        this.errorMessage = "An error occurred";
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
