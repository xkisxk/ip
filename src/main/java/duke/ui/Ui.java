package duke.ui;

import duke.command.DukeException;
import duke.command.TaskManager;
import duke.task.Task;

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

    public void printError(String message) {
        printMessage(LINE_SEPARATOR, message, LINE_SEPARATOR);
    }

    public void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGE);
    }

    public void printWelcomeBackMessage() {
        printMessage(LINE_SEPARATOR, WELCOME_BACK_MESSAGE, LINE_SEPARATOR);
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
            printError(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints this message when a task can't be added
     */
    public void printFailedToAddMessage() {
        System.out.println("There's too much stuff in the task list.\nI can't remember them all.");
    }

    /**
     * Prints this message when a task has been successfully added.
     */
    public void printAddedMessage(ArrayList<Task> taskList) {
        System.out.println("Got it. I have added this task:\n   " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printGoodbyeMessage() {
        printMessage(LINE_SEPARATOR, GOODBYE_MESSAGE, LINE_SEPARATOR);
    }

}
