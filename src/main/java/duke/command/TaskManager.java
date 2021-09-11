package duke.command;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskManager extends Duke {
    protected String command;
    protected String description;
    protected String date;

    public TaskManager(String command, String description, String date) {
        this.command = command;
        this.description = description;
        this.date = date;
    }

    /**
     * Handles commands from the input based on the command word, which is the first word from input.
     */
    public void handleCommand() throws DukeException {
        switch (command) {
        case "todo":
            // Labels task as T
            handleToDo();
            break;
        case "event":
            // Labels task as E and also takes in a date
            handleEvent();
            break;
        case "deadline":
            // Labels task as D and also takes in a date
            handleDeadline();
            break;
        case "done":
            // Marks task x as done where x is the index.
            handleDone();
            break;
        case "list":
            // Lists out all the tasks that are added with the command "list".
            printTaskList();
            break;
        case "bye":
            // Ends conversation
            isChatting = false;
            System.out.println("Bye. Talk to you later!");
            break;
        case "delete":
            // Delete a task
            handleDelete();
            break;
        default:
            throw new DukeException("Sorry I don't understand what you mean by \"" + command + "\"");
        }
    }

    /**
     * Prints out the entire task list as a numbered list.
     */
    private void printTaskList() {
        System.out.println("Here are the tasks in your to do list:");
        int index = 1;
        for (Task task : taskList) {
            String item = index + "." + task.toString();
            System.out.println(item);
            index++;
        }
    }

    private void handleDelete() {
        try {
            int index = Integer.parseInt(description);
            String task = taskList.get(index - 1).toString();
            taskList.remove(index - 1);
            System.out.println("Avoiding doing this task?! Just kidding.\nI've deleted this task:   ");
            System.out.println(task);
            System.out.println("Now you have " + taskList.size() + " items.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no item at that index. You have " + taskList.size() + " items.");
        } catch (NumberFormatException e) {
            System.out.println("\"" + description + "\" is not a number...");
        }
    }

    /**
     * Marks a task as done, so the task will be tagged with a [X]
     */
    private void handleDone() {
        try {
            int index = Integer.parseInt(description);
            taskList.get(index - 1).markDone();
            System.out.println("Good job on completing this task!\nI've marked this task as done:   ");
            System.out.println(taskList.get(index - 1).toString());
        } catch (NullPointerException e) {
            System.out.println("There is no item at that index. You have " + taskList.size() + " items.");
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
            taskList.add(new Deadline(description, date));
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
            taskList.add(new Event(description, date));
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
            taskList.add(new ToDo(description));
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
        System.out.println("Got it. I have added this task:\n   " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

}
