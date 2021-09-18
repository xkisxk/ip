package duke.parser;

public abstract class Parser {
    public abstract String getCommand();
    public abstract String getDescription();
    public abstract String getDate();
    public abstract boolean getDone();
}
