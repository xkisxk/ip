public class InputParser {
    private final String NO_INPUT = "";
    private final String[] words;

    /**
     * Converts the input from command line to command, description, date formatting
     *
     * @param sentence result from scanner.nextLine()
     */
    public InputParser(String sentence) {
        this.words = sentence.split(" ");
    }

    /**
     * Gets the command from input regardless if the command is valid or not.
     * The command should be the first word of the input.
     *
     * @return the command for the input
     */
    public String getCommand() {
        if (words.length == 0)   {
            return NO_INPUT;
        }
        return words[0].toLowerCase();
    }

    /**
     * Gets the description of the task from input which excludes the command
     * and date if there is any.
     *
     * @return the description of the task
     */
    public String getDescription() {
        if (words.length <= 1) {
            return NO_INPUT;
        }
        String description = words[1];
        for (int i = 2; i < words.length; i++) {
            if (words[i].startsWith("/")) {
                break;
            }
            description += " " + words[i];
        }
        return description;
    }

    /**
     * Gets the date of the task if any. This reads anything that comes after
     * the keyword "/by" or "/at"
     *
     * @return the date of an event or deadline
     */
    public String getDate() {
        int keywordIndex = getKeywordIndex();
        if (keywordIndex == -1) {
            return NO_INPUT;
        }
        String dateAsString = words[keywordIndex + 1];
        for (int i = keywordIndex + 2; i < words.length; i++) {
            dateAsString += " " + words[i];
        }
        return dateAsString;
    }

    private int getKeywordIndex() {
        int keywordIndex = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith("/by") || words[i].startsWith("/at")) {
                keywordIndex = i;
                break;
            }
        }
        return keywordIndex;
    }
}
