public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add a deadline that has no description");
        }
        this.by = by;
        if (by.equals(NO_INPUT)) {
            throw new DukeException("I need a date for this deadline.\nUse /by to tell me the date.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
