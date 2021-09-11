package duke;

import duke.command.DukeException;
import duke.command.FileParser;
import duke.command.InputParser;
import duke.command.TaskManager;
import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    protected static boolean isChatting = true;
    protected static final int LIST_SIZE = 100;
    protected static int listIndex = 0;
    protected static Task[] taskList = new Task[LIST_SIZE];
    protected static final String LINE = "______________________________________________\n";
    protected static final String FILE_NAME = "saved.txt";
    protected static final String FILE_PATH = "./data/";
    protected static final String NO_INPUT = "";

    public static void main(String[] args) throws IOException, DukeException {
        initDuke();
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
        } catch (DukeException | IOException e) {
            System.out.println(e.toString());
        }
        System.out.print(LINE);
    }

    private static void printWelcomeMessage() {
        System.out.print(LINE + "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?\n" + LINE);
    }

    /**
     * Initialises Duke, which is creating the directory and file if not yet and print the corresponding
     * welcome message
     *
     * @throws IOException
     */
    private static void initDuke() throws IOException, DukeException {
        File filePath = new File(FILE_PATH);
        File file = new File(FILE_PATH + FILE_NAME);
        String[] previousState = new String[LIST_SIZE];

        filePath.mkdir();
        if (file.createNewFile()) {
            // file created
            printWelcomeMessage();
        } else {
            // file exists
            System.out.println(LINE + "Welcome back! How are you doing?\nThis were the tasks you told me from last time:");
            Scanner content = new Scanner(file);
            int iterator = 0;
            // Convert all the tasks from the save to tasks in Duke
            while (content.hasNextLine()) {
                String line = content.nextLine();
                previousState[iterator] = line;
                iterator++;
                FileParser fileInput = new FileParser(line);
                TaskManager saveToTask = new TaskManager(fileInput.getCommand(), fileInput.getDescription(), fileInput.getDate(), fileInput.getDone());
                saveToTask.convertSaveToTasks();
            }
            TaskManager printPreviousState = new TaskManager("list", NO_INPUT, NO_INPUT);
            printPreviousState.handleCommand();
            System.out.print(LINE);
        }
    }
}
