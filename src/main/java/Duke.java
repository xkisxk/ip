import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "______________________________________________\n";
        System.out.println(line + "........ Oh, sorry! I didn't see you there. I'm Alex\n" + "How may I help you?");
        System.out.print(line);

        boolean isChatting = true;
        while (isChatting)  {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equalsIgnoreCase("bye"))  {
                isChatting = false;
            } else {
                System.out.print(line + input + "\n" + line);
            }
        }

        System.out.println(line + "Bye. Talk to you later!");
        System.out.print(line);
    }
}
