package duke.command;

import duke.exception.DukeException;
import duke.task.*;
import duke.data.Storage;
import duke.parser.Parser;
import duke.ui.Ui;

import java.io.IOException;

import static duke.Duke.PATH;
import static duke.Duke.FILE;

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
     * @param parser   the input parser
     * @param taskList list of tasks
     */
    public TaskManager(Parser parser, TaskList taskList) {
        this.command = parser.getCommand();
        this.description = parser.getDescription();
        this.date = parser.getDate();
        this.isDone = parser.getDone();
        this.taskList = taskList;
        this.ui = new Ui();
    }

    /**
     * Adds the tasks from the saved file to the list, restoring the previous list state
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
     * Executes commands from the input from command line based on the command word, which is the first word from input.
     *
     * @throws DukeException if command is not defined under cases
     * @throws IOException   if save file does not exist
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
            overwriteSaveFile();
            break;
        case "list":
            // Lists out all the tasks that are added with the command "list".
            System.out.println("Here are the tasks in your to do list:");
            ui.printTaskList(taskList);
            break;
        case "bye":
            // Ends conversation
            ui.printGoodbyeMessage();
            overwriteSaveFile();
            break;
        case "delete":
            // Delete a task
            handleDelete();
            overwriteSaveFile();
            break;
        case "save":
            // Manually saves the file
            overwriteSaveFile();
            System.out.println("Saved to saved.txt in ./data");
            break;
        case "find":
            // Finds if task with the description exists
            handleFind();
            break;
        default:
            throw new DukeException("Sorry I don't understand what you mean by \"" + command + "\"");
        }
    }

    private void handleFind() {
        TaskList tasks = taskList.findAllTasks(description);
        if (tasks.isEmpty()) {
            ui.printNotFoundMessage();
        } else {
            ui.printFoundMessage();
            ui.printTaskList(tasks);
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
            System.out.println("There is no item at that index. You have " + taskList.getSize() + " items.");
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
            Task task = taskList.getTask(index - 1);
            task.markDone();
            ui.printDoneMessage(task);
        } catch (NullPointerException e) {
            System.out.println("There is no item at that index. You have " + taskList.getSize() + " items.");
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
            ui.printFailedToAddMessage();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Adds an event task into the task list
     */
    private void handleEvent() {
        try {
            taskList.addTask(new Event(description, date));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printFailedToAddMessage();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Adds a ToDo task into the task list
     */
    private void handleToDo() {
        try {
            taskList.addTask(new ToDo(description));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printFailedToAddMessage();
        } catch (DukeException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Saves everything in the list into a save file, rewriting what the save file had previously.
     *
     * @throws IOException if file does not exist
     */
    public void overwriteSaveFile() throws IOException {
        Storage storage = new Storage(PATH + FILE);
        storage.save(taskList);
    }

    /**
     * Appends a newly added task into the save file, gets called
     *
     * @throws IOException if file does not exist
     */
    private void autoSaveFile() throws IOException {
        Storage storage = new Storage(PATH + FILE);
        storage.appendFile(taskList);
    }
}
