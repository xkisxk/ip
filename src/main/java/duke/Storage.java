package duke;

import duke.command.FileParser;
import duke.command.TaskManager;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected final String NO_INPUT = "";

    private Boolean hasDate(String date) {
        return !date.equals(NO_INPUT);
    }

    /**
     * Saves everything in the list into a save file, rewriting what the save file had previously.
     *
     * @throws IOException if file does not exist
     */
    public void save(String file, ArrayList<Task> taskList) throws IOException {
        FileWriter writer = new FileWriter(file, false);
        for (Task task : taskList) {
            // Rewrite list with all tasks currently in the list
            String date = task.getDate();
            String listItem = task.getTaskTag() + " | " + task.getDoneTag() + " | " + task.getDescription();
            listItem = (hasDate(date)) ? listItem + " | " + date : listItem;
            listItem += "\n";
            writer.write(listItem);
        }
        writer.flush();
        writer.close();
    }

    /**
     * Appends a newly added task into the save file.
     *
     * @throws IOException if file does not exist
     */
    public void autoSave(String file, ArrayList<Task> taskList) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        Task task = taskList.get(taskList.size() - 1);

        // Get the list item
        String date = task.getDate();
        String listItem = task.getTaskTag() + " | " + task.getDoneTag() + " | " + task.getDescription();
        listItem = (hasDate(date)) ? listItem + " | " + date : listItem;
        listItem += "\n";

        // Append it
        writer.write(listItem);
        writer.flush();
        writer.close();
    }

    public void load(String path, String filePath) throws IOException {
        File dir = new File(path);
        File file = new File(filePath);
        dir.mkdir();
        file.createNewFile();

        Scanner content = new Scanner(file);
        // Convert all the tasks from the save file to tasks in Duke
        addSavedToTaskList(content);
        TaskManager previousState = new TaskManager();
        previousState.printTaskList();
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
