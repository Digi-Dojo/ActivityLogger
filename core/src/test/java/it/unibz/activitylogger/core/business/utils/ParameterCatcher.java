package it.unibz.activitylogger.core.business.utils;

import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.business.LogRecordSaver;

public class ParameterCatcher implements LogRecordSaver {
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
