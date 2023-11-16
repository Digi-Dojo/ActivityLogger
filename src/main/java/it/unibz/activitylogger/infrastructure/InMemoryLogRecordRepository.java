package it.unibz.activitylogger.infrastructure;

import it.unibz.activitylogger.api.LogRecord;
import it.unibz.activitylogger.business.LogRecordRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryLogRecordRepository implements LogRecordRepository {
    private final Map<String, PersistableLogRecord> logRecords = new HashMap<>();

    @Override
    public void save(LogRecord logRecord) {
        PersistableLogRecord persistable = PersistableLogRecord.from(logRecord);
        logRecords.put(persistable.getId(), persistable);
    }
}
