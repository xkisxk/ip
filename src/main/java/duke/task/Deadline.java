package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructor used for creating a deadline from TaskManager
     *
     * @param description    description of task
     * @param by             date of task
     * @throws DukeException when there is no description or date
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a deadline that has no description");
        }
        this.by = by;
        if (by.equals(NO_INPUT)) {
            throw new DukeException("I need a date for this deadline.\nUse /by to tell me the date.");
        }
    }

    /**
     * Constructor used for converting deadline from save file to deadline in Duke
     *
     * @param description    description of task
     * @param isDone         status of task
     * @param by             date of task
     * @throws DukeException when there is no description or date
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a deadline that has no description");
        }
        this.by = by;
        if (by.equals(NO_INPUT)) {
            throw new DukeException("I need a date for this deadline.\nUse /by to tell me the date.");
        }
    }

    @Override
    public String getTaskTag() {
        return "D";
    }

    @Override
    public String getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
