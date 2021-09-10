package duke;

import duke.command.DukeException;
import duke.command.InputParser;
import duke.command.TaskManager;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class    Duke {
    protected static boolean isChatting = true;
    protected static int listIndex = 0;
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    protected static final String LINE = "______________________________________________\n";

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner input = new Scanner(System.in);
        while (isChatting) {
            String sentence = input.nextLine();
            InputParser parsedInput = new InputParser(sentence);
            TaskManager taskManager = new TaskManager(parsedInput.getCommand(), parsedInput.getDescription(), parsedInput.getDate());
            printResponseMessage(taskManager);
        }
    }

    private static void printResponseMessage(TaskManager taskManager) {
        System.out.print(LINE);
        try {
            taskManager.handleCommand();
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.print(LINE);
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE + "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?");
        System.out.print(LINE);
    }
}
