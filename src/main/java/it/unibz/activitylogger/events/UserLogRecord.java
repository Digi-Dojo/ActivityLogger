package it.unibz.activitylogger.events;

public class UserLogRecord extends LogRecord {
    public static final String SOURCE_TYPE_NAME = "USER";

    public static LogRecord extract(Event event) {
        String userId = event.getBody().get("id").toString();
        String action = event.getEventType();
        Long timestamp = event.getTimestamp();

        return new UserLogRecord(userId, action, timestamp);
    }

    public final String userId;
    public final String action;
    public final Long timestamp;

    private UserLogRecord(String userId, String action, Long timestamp) {
        this.userId = userId;
        this.action = action;
        this.timestamp = timestamp;
    }
}
