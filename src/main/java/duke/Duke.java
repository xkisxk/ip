package duke;

import duke.command.DukeException;
import duke.command.FileParser;
import duke.command.InputParser;
import duke.command.TaskManager;
import duke.task.Task;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    protected static boolean isChatting = true;
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static final String LINE = "______________________________________________\n";
    protected static final String FILE_NAME = "saved.txt";
    protected static final String FILE_PATH = "./data/";
    protected static final String NO_INPUT = "";

    public static void main(String[] args) throws IOException {
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
            System.out.println(e);
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
     * @throws IOException if file or dir does not exist
     */
    private static void initDuke() throws IOException {
        File filePath = new File(FILE_PATH);
        File file = new File(FILE_PATH + FILE_NAME);

        filePath.mkdir();
        if (file.createNewFile()) {
            // file created
            printWelcomeMessage();
        } else {
            // file exists
            System.out.println(LINE + "Welcome back! How are you doing?\nHere are the tasks from last time:");
            Scanner content = new Scanner(file);
            // Convert all the tasks from the save file to tasks in Duke
            addSavedToTaskList(content);
            TaskManager previousState = new TaskManager();
            previousState.printTaskList();
            System.out.print(LINE);
        }
    }

    /**
     * Converts the tasks from the saved formatting to Duke task formatting
     *
     * @param content input from saved file
     */
    private static void addSavedToTaskList(Scanner content) {
        while (content.hasNextLine()) {
            String line = content.nextLine();
            FileParser fileInput = new FileParser(line);
            TaskManager toTask = new TaskManager(fileInput.getCommand(), fileInput.getDescription(), fileInput.getDate(), fileInput.getDone());
            toTask.convertSaveToTasks();
        }
    }
}
