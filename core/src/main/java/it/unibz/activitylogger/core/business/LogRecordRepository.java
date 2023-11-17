package it.unibz.activitylogger.core.business;

import it.unibz.activitylogger.core.api.LogRecord;

public interface LogRecordRepository {
    void save(LogRecord logRecord);
}
