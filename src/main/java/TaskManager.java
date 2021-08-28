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
    public void handleCommand() {
        switch (command) {
        case "todo":
            // Labels task as T
            if (listIndex < listSize) {
                taskList[listIndex] = new ToDo(description);
                listIndex++;
                printAddedMessage();
            } else {
                printFailedToAddMessage();
            }
            break;
        case "event":
            // Labels task as E and also takes in a date
            if (listIndex < listSize) {
                taskList[listIndex] = new Event(description, date);
                listIndex++;
                printAddedMessage();
            } else {
                printFailedToAddMessage();
            }
            break;
        case "deadline":
            // Labels task as D and also takes in a date
            if (listIndex < listSize) {
                taskList[listIndex] = new Deadline(description, date);
                listIndex++;
                printAddedMessage();
            } else {
                printFailedToAddMessage();
            }
            break;
        case "done":
            // Marks task x as done where x is the index.
            int index = Integer.parseInt(description);
            if (index <= listIndex) {
                taskList[index - 1].markDone();
                System.out.println("Good job on completing this task!\nI've marked this task as done:");
                System.out.println(taskList[index - 1].toString());
            } else {
                System.out.println("There is no item on that index.");
            }
            break;
        case "list":
            // Lists out all the tasks that are added with the command "list".
            System.out.println("Here are the tasks in your to do list:");
            for (int i = 0; i < listIndex; i++) {
                String item = (i + 1) + "." + taskList[i].toString();
                System.out.println(item);
            }
            break;
        case "bye":
            // Ends conversation
            isChatting = false;
            System.out.println("Bye. Talk to you later!");
            break;
        default:
            System.out.println("I don't understand what you mean");
        }
    }

    /**
     * Prints this message when a task can't be added
     */
    private void printFailedToAddMessage() {
        System.out.println("There's too much stuff in the to do list, I can't remember them all.");
    }

    /**
     * Prints this message when a task has been successfully added.
     */
    private void printAddedMessage() {
        System.out.println("Got it. I have added this task: \n   " + taskList[listIndex - 1]);
        System.out.println("Now you have " + listIndex + " tasks in the list");
    }

}
