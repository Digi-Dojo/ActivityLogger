package it.unibz.activitylogger.events;

public abstract class LogRecord {
    public static LogRecord extract(Event event) {
        switch (event.getSourceType().toUpperCase()) {
            case UserLogRecord.SOURCE_TYPE_NAME -> {
                return UserLogRecord.extract(event);
            }
            default -> {
                return GenericLogRecord.extract(event);
            }
        }
    }

    protected LogRecord() {}
}
