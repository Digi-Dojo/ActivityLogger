package it.unibz.activitylogger.core.api;

public interface LogRecord {
    String getContextName();
    String getContextId();

    String getEntityTypeName();
    String getEntityId();

    String getAction();

    Object getSemiStructuredData();
}
