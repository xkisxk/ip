import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "______________________________________________\n";
        System.out.println(line + "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?");
        System.out.print(line);

        int listSize = 100;
        int listIndex = 0;
        Task[] toDoList = new Task[listSize];

        boolean isChatting = true;

        while (isChatting) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            System.out.print(line);
            if (input.equalsIgnoreCase("bye")) {
                isChatting = false;
                System.out.println("Bye. Talk to you later!");
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your to do list:");
                for (int i = 0; i < listIndex; i++) {
                    String item = i + 1 + ".[" + toDoList[i].getStatusIcon() + "] " + toDoList[i].getDescription();
                    System.out.println(item);
                }
            } else if (input.toLowerCase().contains("done")) {
                String[] words = input.split("\\s+");
                int index = Integer.parseInt(words[1]);

                if (index <= listIndex) {
                    toDoList[index - 1].markDone();
                    System.out.println("Good job on completing this task!\nI've marked this task as done:");
                    System.out.println("[X] " + toDoList[index - 1].getDescription());
                } else {
                    System.out.println("There is no item on that index.");
                }
            } else {
                if (listIndex < listSize) {
                    toDoList[listIndex] = new Task(input);
                    listIndex++;
                    System.out.println("Added: \"" + input + "\" into the list");
                } else {
                    System.out.println("There's too much stuff in the to do list, I can't remember them all.");
                }
            }
            System.out.print(line);
        }

    }
}
