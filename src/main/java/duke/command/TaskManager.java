package duke.command;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager extends Duke {
    protected String command;
    protected String description;
    protected String date;
    protected boolean isDone;

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

    /**
     * Adds the tasks from the saved file to the list, essentially restoring the previous list state
     *
     * @throws DukeException when formatting does not fit Duke
     */
    public void convertSaveToTasks() throws DukeException {
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
        default:
        }
    }

    /**
     * Converts ToDo from the saved file to a ToDo task
     *
     * @throws DukeException
     */
    private void convertToDo() throws DukeException {
        try {
            taskList[listIndex] = new ToDo(description, isDone);
            listIndex++;
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        }
    }

    /**
     * Converts event from the saved file to an event task
     *
     * @throws DukeException
     */
    private void convertEvent() throws DukeException {
        try {
            taskList[listIndex] = new Event(description, isDone, date);
            listIndex++;
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        }
    }

    /**
     * Converts deadline from the saved file to a deadline task
     *
     * @throws DukeException
     */
    private void convertDeadline() throws DukeException {
        try {
            taskList[listIndex] = new Deadline(description, isDone, date);
            listIndex++;
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        }
    }

    /**
     * Handles commands from the input from command line based on the command word, which is the first word from input.
     */
    public void handleCommand() throws DukeException, IOException {
        switch (command) {
        case "todo":
            // Labels task as T
            handleToDo();
            autoSave();
            break;
        case "event":
            // Labels task as E and also takes in a date
            handleEvent();
            autoSave();
            break;
        case "deadline":
            // Labels task as D and also takes in a date
            handleDeadline();
            autoSave();
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
        case "save":
            // Manually saves the file
            saveFile();
            System.out.println("Saved to saved.txt in ./data");
        default:
            throw new DukeException("Sorry I don't understand what you mean by \"" + command + "\"");
        }
    }

    private Boolean hasDate(String date) {
        return !date.equals(NO_INPUT);
    }

    public void saveFile() throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH + FILE_NAME, false);
        for (int i = 0; i < listIndex; i++) {
            String date = taskList[i].getDate();
            String listItem = taskList[i].getTaskTag() + " | " + taskList[i].getDoneTag() + " | " + taskList[i].getDescription();
            listItem = (hasDate(date)) ? listItem + " | " + date : listItem;
            listItem += "\n";
            writer.write(listItem);
        }
        writer.flush();
        writer.close();
    }

    private void autoSave() throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH + FILE_NAME, true);
        Task task = taskList[listIndex - 1];
        String date = task.getDate();
        String listItem = task.getTaskTag() + " | " + task.getDoneTag() + " | " + task.getDescription();
        listItem = (hasDate(date)) ? listItem + " | " + date : listItem;
        listItem += "\n";
        writer.write(listItem);
        writer.flush();
        writer.close();
    }

    /**
     * Prints out the entire task list as a numbered list.
     */
    private void printTaskList() {
        for (int i = 0; i < listIndex; i++) {
            String item = (i + 1) + "." + taskList[i].toString();
            System.out.println(item);
        }
    }

    /**
     * Marks a task as done, so the task will be tagged with a [X]
     */
    private void handleDone() {
        try {
            int index = Integer.parseInt(description);
            taskList[index - 1].markDone();
            System.out.println("Good job on completing this task!\nI've marked this task as done:");
            System.out.println(taskList[index - 1].toString());
        } catch (NullPointerException e) {
            System.out.println("There is no item at that index. You have " + listIndex + " items");
        } catch (NumberFormatException e) {
            System.out.println("\"" + description + "\" is not a number...");
        }
    }

    /**
     * Adds a deadline task into the task list
     *
     * @throws DukeException If description is empty, specifically "".
     */
    private void handleDeadline() throws DukeException {
        try {
            taskList[listIndex] = new Deadline(description, date);
            listIndex++;
            printAddedMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        }
    }

    /**
     * Adds an event task into the task list
     *
     * @throws DukeException If description is empty, specifically "".
     */
    private void handleEvent() throws DukeException {
        try {
            taskList[listIndex] = new Event(description, date);
            listIndex++;
            printAddedMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        }
    }

    /**
     * Adds a ToDo task into the task list
     *
     * @throws DukeException If description is empty, specifically "".
     */
    private void handleToDo() throws DukeException {
        try {
            taskList[listIndex] = new ToDo(description);
            listIndex++;
            printAddedMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            printFailedToAddMessage();
        }
    }

    /**
     * Prints this message when a task can't be added
     */
    private void printFailedToAddMessage() {
        System.out.println("There's too much stuff in the task list.\nI can't remember them all.");
    }

    /**
     * Prints this message when a task has been successfully added.
     */
    private void printAddedMessage() {
        System.out.println("Got it. I have added this task:\n   " + taskList[listIndex - 1]);
        System.out.println("Now you have " + listIndex + " tasks in the list");
    }

}
