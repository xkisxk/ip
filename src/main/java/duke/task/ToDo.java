package duke.task;

import duke.command.DukeException;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a todo that has no description");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
