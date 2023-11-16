package it.unibz.activitylogger.business;

import it.unibz.activitylogger.api.LogRecord;

public interface LogRecordRepository {
    void save(LogRecord logRecord);
}
