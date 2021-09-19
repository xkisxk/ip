package duke.task;

public class Task {
    protected final String NO_INPUT = "";
    protected String description;
    protected boolean isDone;

    /**
     * Task is class that contains a description and status of the task.
     * The status is represented by a [X] for done and a [ ] for not done
     * and is displayed before the description.
     * The task type is represented by a [T] for ToDo, [E] for event and
     * [D] for deadline which is added in the respective subclasses
     * <p>
     * E.g. 1. [T][ ] buy book
     *
     * @param description description or name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the date of the task if it has one
     *
     * @return date
     */
    public String getDate() {
        return NO_INPUT;
    }

    /**
     * Gets the date of the task in MMM d YYYY format
     *
     * @return date
     */
    public String getDateAsFormatted() {
        return NO_INPUT;
    }

    /**
     * Gets the tag of the task, e.g. T for ToDo
     *
     * @return task tag
     */
    public String getTaskTag() {
        return NO_INPUT;
    }

    /**
     * Returns "X" or " " depending on whether the task is completed.
     * "X" for completed, " " for not completed
     *
     * @return the icon representing the status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns 1 or 0 depending on whether the task is completed.
     * 1 for completed, 0 for not completed
     *
     * @return an integer representing the status
     */
    public int getDoneTag() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns the description of the task
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done by changing the boolean isDone to true.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Formats the description of the task
     * e.g [X] buy book
     *
     * @return formatted version of the description
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
