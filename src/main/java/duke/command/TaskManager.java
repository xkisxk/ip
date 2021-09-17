package duke.command;

import duke.Duke;
import duke.data.Storage;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;

public class TaskManager extends Duke {
    protected String command;
    protected String description;
    protected String date;
    protected boolean isDone;

    /**
     * TaskManager handles all the commands from input that comes from both CLI and save file.
     * If the command exists, task manager will execute it, else it will raise an error.
     *
     * @param command command term
     * @param description description of task
     * @param date date of task
     */
    public TaskManager(String command, String description, String date) {
        this.command = command;
        this.description = description;
        this.date = date;
        this.isDone = false;
    }

    public TaskManager(String command, String description, String date, boolean isDone) {
        this.command = command;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
    }

    public TaskManager() {
        this.command = NO_INPUT;
        this.description = NO_INPUT;
        this.date = NO_INPUT;
        this.isDone = false;
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
        Ui ui = new Ui();
        try {
            taskList.add(new ToDo(description, isDone));
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
        Ui ui = new Ui();
        try {
            taskList.add(new Event(description, isDone, date));
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
        Ui ui = new Ui();
        try {
            taskList.add(new Deadline(description, isDone, date));
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
            isChatting = false;
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
            int index = Integer.parseInt(description);
            String task = taskList.get(index - 1).toString();
            taskList.remove(index - 1);
            System.out.println("Avoiding doing this task?! Just kidding.\nI've deleted this task:   ");
            System.out.println(task);
            System.out.println("Now you have " + taskList.size() + " items.");
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
            taskList.get(index - 1).markDone();
            System.out.println("Good job on completing this task!\nI've marked this task as done:   ");
            System.out.println(taskList.get(index - 1).toString());
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
        Ui ui = new Ui();
        try {
            taskList.add(new Deadline(description, date));
            ui.printAddedMessage(taskList);
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
        Ui ui = new Ui();
        try {
            taskList.add(new Event(description, date));
            ui.printAddedMessage(taskList);
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
        Ui ui = new Ui();
        try {
            taskList.add(new ToDo(description));
            ui.printAddedMessage(taskList);
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
    public void saveFile() throws IOException {
        Storage storage = new Storage();
        storage.save(PATH + FILE, taskList);
    }

    /**
     * Appends a newly added task into the save file.
     *
     * @throws IOException if file does not exist
     */
    private void autoSaveFile() throws IOException {
        Storage storage = new Storage();
        storage.autoSave(PATH + FILE, taskList);
    }

    /**
     * Prints out the entire task list as a numbered list.
     */
    public void printTaskList() {
        int index = 1;
        for (Task task : taskList) {
            String item = index + "." + task.toString();
            System.out.println(item);
            index++;
        }
    }

}
