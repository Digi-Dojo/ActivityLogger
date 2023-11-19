package it.unibz.activitylogger.core.business;

import it.unibz.activitylogger.core.api.LogRecord;

public interface LogRecordSaver {
    void save(LogRecord logRecord);
}
