package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    private final Ui ui = new Ui();

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        this.taskList = loadedTasks;
    }

    /**
     * Adds the task and prints a task added message
     *
     * @param t task
     */
    public void addTask(Task t) {
        taskList.add(t);
        ui.printAddedMessage(t);
        ui.printTaskListSize(taskList);
    }

    /**
     * Finds all tasks that contains the given description or date
     * Allows both MMM DD YYYY and YYYY-MM-DD formats for date
     * Case insensitive
     *
     * @param description description/date of task
     * @return list of tasks containing that description
     */
    public TaskList findAllTasks(String description) {
        TaskList tasks = new TaskList();
        for (Task task : taskList) {
            if (hasTask(description, task)) {
                tasks.convertTask(task);
            }
        }
        return tasks;
    }

    private boolean hasTask(String description, Task task) {
        // Make search case-insensitive
        String query = description.toLowerCase();
        String date = task.getDateAsFormatted().toLowerCase();
        String taskDescription = task.getDescription().toLowerCase();

        boolean hasDescription = taskDescription.contains(query);
        boolean hasDate = task.getDate().contains(description) || date.contains(query);

        return hasDescription || hasDate;
    }

    /**
     * Silently adds Task into the list
     *
     * @param t task
     */
    public void convertTask(Task t) {
        taskList.add(t);
    }

    /**
     * gets the tasklist as an ArrayList<Task>
     *
     * @return taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Deletes the task from the list and prints delete task message
     *
     * @param index of the task
     */
    public void deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.printDeleteMessage(task);
        ui.printTaskListSize(taskList);
    }

    /**
     * Gets the task inside the list using the index
     *
     * @throws DukeException if index is out of bounds
     * @param index of the task
     * @return task at that index
     */
    public Task getTask(int index) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("There is no item at that index. You have " + taskList.size() + " items.");
        }
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Checks if the list is empty
     *
     * @return emptiness of list
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
