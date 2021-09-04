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
     * Returns the description of the task
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task
     *
     * @return is done
     */
    public boolean getIsDone() {
        return isDone;
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
