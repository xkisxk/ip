package duke;

import duke.data.Storage;
import duke.parser.InputParser;
import duke.command.TaskManager;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    protected static boolean isChatting = true;
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static final String FILE = "saved.txt";
    protected static final String PATH = "./data/";
    protected static final String NO_INPUT = "";

    protected Ui ui;
    protected Storage storage;


    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        this.ui = new Ui();
        this.storage = new Storage();

        ui.printLine();
        ui.printWelcomeMessage();
        try {
            storage.load(PATH, PATH + FILE);
        } catch (IOException e) {
            ui.printError(e.toString());
        }
        ui.printLine();

        while (isChatting) {
            String sentence = ui.getUserMessage();
            InputParser parsedInput = new InputParser(sentence);
            TaskManager taskManager = new TaskManager(parsedInput.getCommand(), parsedInput.getDescription(), parsedInput.getDate());
            ui.printResponseMessage(taskManager);
        }
    }
}
