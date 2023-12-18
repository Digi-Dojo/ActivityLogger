package it.unibz.activitylogger.persistence.lib;

import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.api.LogRecordSaver;
import it.unibz.activitylogger.persistence.api.DefaultImplementation;
import it.unibz.activitylogger.persistence.utils.PersistableLogRecord;

import java.util.HashMap;
import java.util.Map;

@DefaultImplementation
public class InMemoryLogRecordRepository implements LogRecordSaver {
    private final Map<String, PersistableLogRecord> logRecords = new HashMap<>();

    @Override
    public void save(LogRecord logRecord) {
        PersistableLogRecord persistable = PersistableLogRecord.from(logRecord);
        logRecords.put(persistable.getId(), persistable);
    }
}
