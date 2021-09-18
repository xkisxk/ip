package duke.parser;

public class FileParser extends Parser {
    protected final String[] words;

    /**
     * Converts the input from the saved text file back to command, description, date formatting
     *
     * @param sentence result from scanner.nextLine()
     */
    public FileParser(String sentence) {
        this.words = sentence.split("\\|");
    }

    public String getCommand() {
        return words[0].replaceAll(" ", "").toLowerCase();
    }

    public boolean getDone() {
        return words[1].replaceAll(" ", "").equals("1");
    }

    public String getDescription() {
        return words[2].trim();
    }

    public String getDate() {
        if (words.length > 3) {
            return words[3].trim();
        }
        return "";
    }
}
