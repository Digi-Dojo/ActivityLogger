package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.infrastructure.InMemoryLogRecordRepository;

public class LoggerProxyRepository extends InMemoryLogRecordRepository {
    private final InMemoryLogRecordRepository wrapped;

    public LoggerProxyRepository(InMemoryLogRecordRepository wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void save(LogRecord logRecord) {
        wrapped.save(logRecord);
    }
}
