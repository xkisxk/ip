package duke.parser;

public abstract class Parser {
    /**
     * Gets the command from the input
     *
     * @return command
     */
    public abstract String getCommand();

    /**
     * Gets the description of the task from input
     *
     * @return description
     */
    public abstract String getDescription();

    /**
     * Gets the date of the task from input
     *
     * @return date
     */
    public abstract String getDate();

    /**
     * Gets the done status of the task from input
     *
     * @return done status
     */
    public abstract boolean getDone();

    /**
     * Gets the date keyword which are either "/at" or "/by"
     *
     * @return
     */
    public abstract String getDateKeyword();
}
