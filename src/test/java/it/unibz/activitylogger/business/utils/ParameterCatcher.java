package it.unibz.activitylogger.business.utils;

import it.unibz.activitylogger.api.LogRecord;
import it.unibz.activitylogger.business.LogRecordRepository;

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
