package duke;

import duke.exception.DukeException;
import duke.data.Storage;
import duke.parser.InputParser;
import duke.command.TaskManager;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    public static final String FILE = "saved.txt";
    public static final String PATH = "./data/";
    public static final String NO_INPUT = "";

    protected Ui ui;
    protected Storage storage;
    protected TaskList taskList;

    public static void main(String[] args) {
        new Duke(PATH + FILE).run();
    }

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load(PATH));
        } catch (IOException e) {
            ui.printError(e.getMessage());
        } catch (DukeException e) {
            ui.printError(e.toString());
            taskList = new TaskList();
        }
    }

    private void run() {
        ui.printLine();

        if (storage.isEmpty()) {
            ui.printWelcomeMessage();
        } else {
            ui.printWelcomeBackMessage();
        }
        ui.printTaskList(taskList);

        ui.printLine();

        boolean isChatting = true;
        while (isChatting) {
            String sentence = ui.getUserMessage();
            InputParser parsedInput = new InputParser(sentence);
            TaskManager taskManager = new TaskManager(parsedInput, taskList);
            ui.printResponseMessage(taskManager);

            if (parsedInput.getCommand().equals("bye")) {
                isChatting = false;
            }
        }
    }
}
