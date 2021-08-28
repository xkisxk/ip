import java.util.Scanner;

public class Duke {
    protected static boolean isChatting = true;
    protected static int listSize = 100;
    protected static int listIndex = 0;
    protected static Task[] taskList = new Task[listSize];
    protected static final String line = "______________________________________________\n";

    public static void main(String[] args) {
        printWelcomeMessage();
        while (isChatting) {
            Scanner input = new Scanner(System.in);
            String sentence = input.nextLine();
            InputParser parsedInput = new InputParser(sentence);
            TaskManager taskManager = new TaskManager(parsedInput.getCommand(), parsedInput.getDescription(), parsedInput.getDate());
            printResponseMessage(taskManager);
        }
    }

    private static void printResponseMessage(TaskManager taskManager) {
        System.out.print(line);
        taskManager.handleCommand();
        System.out.print(line);
    }

    private static void printWelcomeMessage() {
        System.out.println(line + "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?");
        System.out.print(line);
    }
}
