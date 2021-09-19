package duke.task;

import duke.exception.DukeException;

public class ToDo extends Task {
    /**
     * Constructor used for creating a ToDo from TaskManager
     *
     * @param description    description of task
     * @throws DukeException when there is no description or date
     */
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a todo that has no description");
        }
    }

    /**
     * Constructor used for converting ToDo from save file to ToDo in Duke
     *
     * @param description    description of task
     * @param isDone         status of task
     * @throws DukeException when there is no description or date
     */
    public ToDo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a todo that has no description");
        }
    }

    @Override
    public String getTaskTag() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
