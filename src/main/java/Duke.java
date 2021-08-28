public class Duke {
    static boolean isChatting = true;
    static int listSize = 100;
    static int listIndex = 0;
    static Task[] taskList = new Task[listSize];

    public static void main(String[] args) {
        final String line = "______________________________________________\n";
        System.out.println(line + "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?");
        System.out.print(line);

        while (isChatting) {
            InputParser parsedInput = new InputParser();
            TaskManager taskManager = new TaskManager(parsedInput.getCommand(), parsedInput.getDescription(), parsedInput.getDate());

            System.out.print(line);
            taskManager.handleCommand();
            System.out.print(line);
        }
    }
}
