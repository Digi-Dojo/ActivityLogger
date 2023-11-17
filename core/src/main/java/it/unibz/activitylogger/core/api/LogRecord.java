package it.unibz.activitylogger.core.api;

public interface LogRecord {
    String getContextName();
    String getContextId();

    String getEntityName();
    String getEntityId();

    String getAction();

    Object getSemiStructuredData();
}
