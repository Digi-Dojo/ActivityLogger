package it.unibz.activitylogger.events;

public class NoteLogRecord extends LogRecord {
    public static final String SOURCE_TYPE_NAME = "NOTE";

    public static LogRecord extract(Event event) {
        System.out.println(event);
        String noteId = event.getBody().get("noteId").toString();
        String authorId = event.getBody().get("authorId").toString();
        String action = event.getEventType();
        Long timestamp = event.getTimestamp();

        return new NoteLogRecord(noteId, authorId, action, timestamp);
    }

    public final String noteId;
    public final String authorId;
    public final String action;
    public final Long timestamp;

    private NoteLogRecord(String noteId, String authorId, String action, Long timestamp) {
        this.noteId = noteId;
        this.authorId = authorId;
        this.action = action;
        this.timestamp = timestamp;
    }
}
