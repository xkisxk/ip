import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "______________________________________________\n";
        System.out.println(line + "...... Oh, sorry! I didn't see you there.\nI'm Alex. How may I help you?");
        System.out.print(line);

        String[] list = new String[100];
        int listIndex = 0;

        boolean isChatting = true;

        while (isChatting) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                isChatting = false;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.print(line);
                for (int i = 0; i < listIndex; i++) {
                    System.out.println(i+1 + ". " + list[i]);
                }
                System.out.print(line);
            } else {
                list[listIndex] = input;
                listIndex++;
                System.out.print(line + "Added: \"" + input + "\" into the list\n" + line);
            }
        }

        System.out.print(line + "Bye. Talk to you later!\n" + line);
    }
}
