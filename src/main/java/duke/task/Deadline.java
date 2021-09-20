package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

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
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            if (by.equals(NO_INPUT)) {
                throw new DukeException("I need a date for this deadline.\nUse /by to tell me the date.");
            }
            throw new DukeException("Date in incorrect format, please give in YYYY-MM-DD format");
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
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            if (by.equals(NO_INPUT)) {
                throw new DukeException("I need a date for this deadline.\nUse /by to tell me the date.");
            }
            throw new DukeException("Date in incorrect format, please give in YYYY-MM-DD format");
        }
    }

    @Override
    public String getTaskTag() {
        return "D";
    }

    @Override
    public String getDate() {
        return by.toString();
    }

    @Override
    public String getDateAsFormatted() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateAsFormatted() + ")";
    }
}
