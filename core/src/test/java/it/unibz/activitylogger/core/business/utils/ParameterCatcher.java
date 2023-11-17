package it.unibz.activitylogger.core.business.utils;

import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.business.LogRecordRepository;

public class ParameterCatcher implements LogRecordRepository {
    private LogRecord given = null;

    @Override
    public void save(LogRecord logRecord) {
        given = logRecord;
    }

    public LogRecord getGivenLogRecord() {
        return given;
    }

    public boolean saveHaveBeenCalled() {
        return given != null;
    }
}
