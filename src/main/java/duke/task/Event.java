package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventAt;

    /**
     * Constructor used for creating an event from TaskManager
     *
     * @param description    description of task
     * @param eventAt             date of task
     * @throws DukeException when there is no description or date
     */
    public Event(String description, String eventAt) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add an event that has no description");
        }
        try {
            this.eventAt = LocalDate.parse(eventAt);
        } catch (DateTimeException e) {
            if (eventAt.equals(NO_INPUT)) {
                throw new DukeException("I need a date for this event.\nUse /at to tell me the date.");
            }
            throw new DukeException("Date in incorrect format, please give in YYYY-MM-DD format");
        }
    }

    /**
     * Constructor used for converting events from save file to events in Duke
     *
     * @param description    description of task
     * @param isDone         status of task
     * @param eventAt             date of task
     * @throws DukeException when there is no description or date
     */
    public Event(String description, boolean isDone, String eventAt) throws DukeException {
        super(description, isDone);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add an event that has no description");
        }
        try {
            this.eventAt = LocalDate.parse(eventAt);
        } catch (DateTimeException e) {
            if (eventAt.equals(NO_INPUT)) {
                throw new DukeException("I need a date for this event.\nUse /at to tell me the date.");
            }
            throw new DukeException("Date in incorrect format, please give in YYYY-MM-DD format");
        }
    }

    @Override
    public String getTaskTag() {
        return "E";
    }

    @Override
    public String getDate() {
        return eventAt.toString();
    }

    @Override
    public String getDateAsFormatted() {
        return eventAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateAsFormatted() + ")";
    }
}
