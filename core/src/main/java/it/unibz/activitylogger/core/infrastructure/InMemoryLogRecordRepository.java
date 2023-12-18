package it.unibz.activitylogger.core.infrastructure;

import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.api.LogRecordSaver;

import java.util.HashMap;
import java.util.Map;

public class InMemoryLogRecordRepository implements LogRecordSaver {
    private final Map<String, PersistableLogRecord> logRecords = new HashMap<>();

    @Override
    public void save(LogRecord logRecord) {
        PersistableLogRecord persistable = PersistableLogRecord.from(logRecord);
        logRecords.put(persistable.getId(), persistable);
    }
}
