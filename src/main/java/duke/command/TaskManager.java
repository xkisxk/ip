package duke.command;

import duke.TaskList;
import duke.data.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.IOException;

import static duke.Duke.PATH;
import static duke.Duke.FILE;
import static duke.Duke.NO_INPUT;

public class TaskManager {
    protected TaskList taskList;
    protected String command;
    protected String description;
    protected String date;
    protected boolean isDone;
    private Ui ui;

    /**
     * TaskManager handles all the commands from input that comes from both CLI and save file.
     * If the command exists, task manager will execute it, else it will raise an error.
     *
     * @param command command term
     * @param description description of task
     * @param date date of task
     * @param isDone the status of task
     */
    public TaskManager(String command, String description, String date, boolean isDone) {
        this.command = command;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
        this.ui = new Ui();
    }

    public TaskManager(TaskList taskList) {
        this.command = NO_INPUT;
        this.description = NO_INPUT;
        this.date = NO_INPUT;
        this.isDone = false;
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public TaskManager(Parser parser, TaskList taskList) {
        this.command = parser.getCommand();
        this.description = parser.getDescription();
        this.date = parser.getDate();
        this.isDone = parser.getDone();
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Adds the tasks from the saved file to the list, essentially restoring the previous list state
     */
    public void convertSaveToTasks() {
        switch (command) {
        case "t":
            convertToDo();
            break;
        case "e":
            convertEvent();
            break;
        case "d":
            convertDeadline();
            break;
        }
    }

    /**
     * Converts ToDo from the saved file to a ToDo task
     */
    private void convertToDo() {
        try {
            taskList.convertTask(new ToDo(description, isDone));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printFailedToAddMessage();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Converts event from the saved file to an event task
     */
    private void convertEvent() {
        try {
            taskList.convertTask(new Event(description, isDone, date));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printFailedToAddMessage();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Converts deadline from the saved file to a deadline task
     */
    private void convertDeadline() {
        try {
            taskList.convertTask(new Deadline(description, isDone, date));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printFailedToAddMessage();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Handles commands from the input from command line based on the command word, which is the first word from input.
     *
     * @throws DukeException if command is not defined under cases
     * @throws IOException if save file does not exist
     */
    public void handleCommand() throws DukeException, IOException {
        switch (command) {
        case "todo":
            // Labels task as T
            handleToDo();
            autoSaveFile();
            break;
        case "event":
            // Labels task as E and also takes in a date
            handleEvent();
            autoSaveFile();
            break;
        case "deadline":
            // Labels task as D and also takes in a date
            handleDeadline();
            autoSaveFile();
            break;
        case "done":
            // Marks task x as done where x is the index.
            handleDone();
            saveFile();
            break;
        case "list":
            // Lists out all the tasks that are added with the command "list".
            System.out.println("Here are the tasks in your to do list:");
            printTaskList();
            break;
        case "bye":
            // Ends conversation
            System.out.println("Bye. Talk to you later!");
            saveFile();
            break;
        case "delete":
            // Delete a task
            handleDelete();
            saveFile();
            break;
        case "save":
            // Manually saves the file
            saveFile();
            System.out.println("Saved to saved.txt in ./data");
        default:
            throw new DukeException("Sorry I don't understand what you mean by \"" + command + "\"");
        }
    }

    /**
     * Deletes a task from the list, the number given is the index of the task that will be deleted
     */
    private void handleDelete() {
        try {
            int index = Integer.parseInt(description) - 1;
            taskList.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no item at that index. You have " + taskList.size() + " items.");
        } catch (NumberFormatException e) {
            System.out.println("\"" + description + "\" is not a number...");
        }
    }

    /**
     * Marks a task as done, so the task will be tagged with a [X]
     */
    private void handleDone() {
        try {
            int index = Integer.parseInt(description);
            taskList.getTask(index - 1).markDone();
            System.out.println("Good job on completing this task!\nI've marked this task as done:   ");
            System.out.println(taskList.getTask(index - 1).toString());
        } catch (NullPointerException e) {
            System.out.println("There is no item at that index. You have " + taskList.size() + " items.");
        } catch (NumberFormatException e) {
            System.out.println("\"" + description + "\" is not a number...");
        }
    }

    /**
     * Adds a deadline task into the task list
     */
    private void handleDeadline() {
        try {
            taskList.addTask(new Deadline(description, date));
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Adds an event task into the task list
     */
    private void handleEvent() {
        try {
            taskList.addTask(new Event(description, date));
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a ToDo task into the task list
     */
    private void handleToDo() {
        try {
            taskList.addTask(new ToDo(description));
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves everything in the list into a save file, rewriting what the save file had previously.
     *
     * @throws IOException if file does not exist
     */
    public void saveFile() throws IOException {
        Storage storage = new Storage(PATH + FILE);
        storage.save(taskList);
    }

    /**
     * Appends a newly added task into the save file.
     *
     * @throws IOException if file does not exist
     */
    private void autoSaveFile() throws IOException {
        Storage storage = new Storage(PATH + FILE);
        storage.autoSave(taskList);
    }

    /**
     * Prints out the entire task list as a numbered list.
     */
    public void printTaskList() {
        int index = 1;
        for (int i = 0; i < taskList.size(); i++) {
            String item = index + "." + taskList.getTask(i).toString();
            System.out.println(item);
            index++;
        }
    }

    /**
     * Prints this message when a task can't be added
     */
    public void printFailedToAddMessage() {
        System.out.println("There's too much stuff in the task list.\nI can't remember them all.");
    }


}
