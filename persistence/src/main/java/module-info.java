import it.unibz.activitylogger.persistence.lib.InMemoryLogRecordRepository;

module it.unibz.activitylogger.persistence {
    requires it.unibz.activitylogger.core;

    exports it.unibz.activitylogger.persistence.api;

    provides it.unibz.activitylogger.core.api.LogRecordSaver
            with InMemoryLogRecordRepository;
}