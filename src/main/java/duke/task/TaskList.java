package duke.task;

import duke.task.Task;
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

    public void addTask(Task t) {
        taskList.add(t);
        ui.printAddedMessage(taskList);
    }

    public TaskList findAllTasks(String description) {
        TaskList tasks = new TaskList();
        for (Task task : taskList) {;
            if (hasTask(description, task)) {
                tasks.convertTask(task);
            }
        }
        return tasks;
    }

    private boolean hasTask(String description, Task task) {
        return task.getDescription().toLowerCase().contains(description.toLowerCase());
    }

    public void convertTask(Task t) {
        taskList.add(t);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void deleteTask(int index) {
        String task = taskList.get(index).toString();
        taskList.remove(index);
        System.out.println("Avoiding doing this task?! Just kidding.\nI've deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " items.");
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
