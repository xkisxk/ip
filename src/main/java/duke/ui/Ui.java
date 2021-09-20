package duke.ui;

import duke.exception.DukeException;
import duke.command.CommandManager;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected final String LINE_SEPARATOR = "____________________________________________________________";
    protected final String WELCOME_MESSAGE = "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?";
    protected final String WELCOME_BACK_MESSAGE = "Welcome back! How are you doing?\nHere are the tasks from last time:";
    protected final String GOODBYE_MESSAGE = "Bye. Talk to you later!";
    protected final String FAILED_TO_ADD_MESSAGE = "There's too much stuff in the task list.\nI can't remember them all.";
    protected final String DONE_MESSAGE = "Good job on completing this task!\nI've marked this task as done:";
    protected final String NOT_FOUND_MESSAGE = "The task you are searching for does not exist";
    protected final String FOUND_MESSAGE = "Here are the matching tasks in your list:";
    protected final String DELETE_MESSAGE = "Avoiding doing this task?! Just kidding.\nI've deleted this task:";
    protected final String SAVED_MESSAGE = "Saved to saved.txt in ./data";
    protected final String OFFSET = "   ";

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

    public void printDeleteMessage(Task task) {
        printMessage(
                DELETE_MESSAGE,
                OFFSET + task.toString());
    }

    public void printSaveMessage() {
        printMessage(SAVED_MESSAGE);
    }

    public void printTaskListSize(ArrayList<Task> taskList) {
        printMessage("Now you have " + taskList.size() + " items.");
    }

    /**
     * Prints this message if the given task description is found
     */
    public void printFoundMessage() {
        printMessage(FOUND_MESSAGE);
    }

    /**
     * Prints this message if the given task description is not found
     */
    public void printNotFoundMessage() {
        printMessage(NOT_FOUND_MESSAGE);
    }

    /**
     * Prints this message when an error has occurred
     * @param message error message
     */
    public void printError(String message) {
        printMessage(message);
    }

    /**
     * Prints this message when entering the program with an empty save file
     */
    public void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGE);
    }

    /**
     * Prints this message when entering the program with a nonempty save file
     */
    public void printWelcomeBackMessage() {
        printMessage(WELCOME_BACK_MESSAGE);
    }

    /**
     * Prints a line dividor
     */
    public void printLine() {
        printMessage(LINE_SEPARATOR);
    }

    /**
     * Prints the response message to the given command
     *
     * @param commandManager handles the command
     */
    public void printResponseMessage(CommandManager commandManager) {
        printLine();
        try {
            commandManager.handleCommand();
        } catch (DukeException e) {
            printMessage(e.toString());
        } catch (IOException e) {
            printMessage(e.getMessage());
        }
        printLine();
    }

    public void printFailedToAddMessage() {
        printMessage(FAILED_TO_ADD_MESSAGE);
    }

    public void printAddedMessage(Task task) {
        printMessage(
                "Got it. I have added this task:",
                OFFSET + task);
    }

    /**
     * Prints this message when exiting the program
     */
    public void printGoodbyeMessage() {
        printMessage(GOODBYE_MESSAGE);
    }

    public void printDoneMessage(Task task) {
        printMessage(
                DONE_MESSAGE,
                OFFSET + task);
    }

    /**
     * Prints out the entire task list as a numbered list.
     * E.g. 1. [T] buy bread
     *      2. [D] homework (by: 26/4)
     */
    public void printTaskList(TaskList taskList) throws DukeException {
        for (int i = 0; i < taskList.getSize(); i++) {
            String item = i + 1 + "." + taskList.getTask(i).toString();
            printMessage(item);
        }
    }
}
