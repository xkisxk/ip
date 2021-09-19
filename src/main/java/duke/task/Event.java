package duke.task;

import duke.exception.DukeException;

public class Event extends Task {
    protected String at;

    /**
     * Constructor used for creating an event from TaskManager
     *
     * @param description    description of task
     * @param at             date of task
     * @throws DukeException when there is no description or date
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add an event that has no description");
        }
        this.at = at;
        if (at.equals(NO_INPUT)) {
            throw new DukeException("I need a date for this event.\nUse /at to tell me the date.");
        }
    }

    /**
     * Constructor used for converting events from save file to events in Duke
     *
     * @param description    description of task
     * @param isDone         status of task
     * @param at             date of task
     * @throws DukeException when there is no description or date
     */
    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add an event that has no description");
        }
        this.at = at;
        if (at.equals(NO_INPUT)) {
            throw new DukeException("I need a date for this event.\nUse /at to tell me the date.");
        }
    }

    @Override
    public String getTaskTag() {
        return "E";
    }

    @Override
    public String getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
