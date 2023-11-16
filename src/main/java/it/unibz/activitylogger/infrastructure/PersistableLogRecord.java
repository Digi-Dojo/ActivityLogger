package it.unibz.activitylogger.infrastructure;

import it.unibz.activitylogger.api.LogRecord;

import java.util.UUID;

public class PersistableLogRecord implements Identifiable, LogRecord {
    public static PersistableLogRecord from(LogRecord logRecord) {
        String uuid = UUID.randomUUID().toString();
        return new PersistableLogRecord(logRecord, uuid);
    }

    private final String id;
    private final LogRecord wrapped;

    private PersistableLogRecord(LogRecord wrapped, String id) {
        this.wrapped = wrapped;
        this.id = id;
    }

    @Override
    public String getContextName() {
        return wrapped.getContextName();
    }

    @Override
    public String getContextId() {
        return wrapped.getContextId();
    }

    @Override
    public String getEntityName() {
        return wrapped.getEntityName();
    }

    @Override
    public String getEntityId() {
        return wrapped.getEntityId();
    }

    @Override
    public String getAction() {
        return wrapped.getAction();
    }

    @Override
    public Object getSemiStructuredData() {
        return wrapped.getSemiStructuredData();
    }

    @Override
    public String getId() {
        return this.id;
    }
}
