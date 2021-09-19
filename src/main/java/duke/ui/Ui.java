package duke.ui;

import duke.exception.DukeException;
import duke.command.TaskManager;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected final String LINE_SEPARATOR = "_______________________________________________________";
    protected final String WELCOME_MESSAGE = "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?";
    protected final String WELCOME_BACK_MESSAGE = "Welcome back! How are you doing?\nHere are the tasks from last time:";
    protected final String GOODBYE_MESSAGE = "Bye. Talk to you later!";
    protected final String DELETE_MESSAGE = "Avoiding doing this task?! Just kidding.\nI've deleted this task:";
    protected final String FAILED_TO_ADD_MESSAGE = "There's too much stuff in the task list.\nI can't remember them all.";
    protected final String DONE_MESSAGE = "Good job on completing this task!\nI've marked this task as done:";
    protected final String NOT_FOUND_MESSAGE = "The task you are searching for does not exist";
    protected final String FOUND_MESSAGE = "Here are the matching tasks in your list:";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserMessage() {
        return in.nextLine();
    }

    /**
     * Prints all the message that needs to be showed to the user
     *
     * @param message all the strings that are going to be printed
     */
    private void printMessage(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public void printFoundMessage() {
        printMessage(FOUND_MESSAGE);
    }

    public void printNotFoundMessage() {
        printMessage(NOT_FOUND_MESSAGE);
    }

    public void printError(String message) {
        printMessage(message);
    }

    public void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGE);
    }

    public void printWelcomeBackMessage() {
        printMessage(WELCOME_BACK_MESSAGE);
    }

    public void printLine() {
        printMessage(LINE_SEPARATOR);
    }

    public void printResponseMessage(TaskManager taskManager) {
        printLine();
        try {
            taskManager.handleCommand();
        } catch (DukeException e) {
            printMessage(e.toString());
        } catch (IOException e) {
            printMessage(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints this message when a task can't be added
     */
    public void printFailedToAddMessage() {
        printMessage(FAILED_TO_ADD_MESSAGE);
    }

    /**
     * Prints this message when a task has been successfully added.
     */
    public void printAddedMessage(ArrayList<Task> taskList) {
        printMessage("Got it. I have added this task:\n   " + taskList.get(taskList.size() - 1),
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printGoodbyeMessage() {
        printMessage(GOODBYE_MESSAGE);
    }

    public void printDoneMessage(Task task) {
        printMessage(DONE_MESSAGE, task.toString());
    }

    /**
     * Prints out the entire task list as a numbered list.
     */
    public void printTaskList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            String item = i + 1 + "." + taskList.getTask(i).toString();
            printMessage(item);
        }
    }
}
