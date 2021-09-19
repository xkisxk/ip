package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    protected ArrayList<Task> taskList;
    private Ui ui = new Ui();

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
        ui.printAddedMessage(taskList);
    }

    /**
     * Finds all tasks that contains the given description
     *
     * @param description tasks to find
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
        return task.getDescription().toLowerCase().contains(description.toLowerCase());
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
        String task = taskList.get(index).toString();
        taskList.remove(index);
        System.out.println("Avoiding doing this task?! Just kidding.\nI've deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " items.");
    }

    /**
     * Gets the task inside the list using the index
     *
     * @param index of the task
     * @return task at that index
     */
    public Task getTask(int index) {
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
