public class Event extends Task {
    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (description.equals(NO_INPUT)) {
            throw new DukeException("I can't add an event that has no description");
        }
        this.at = at;
        if (at.equals(NO_INPUT)) {
            throw new DukeException("I need a date for this event.\nUse /at to tell me the date.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
