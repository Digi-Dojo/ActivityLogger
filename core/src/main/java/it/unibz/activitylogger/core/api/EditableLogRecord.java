package it.unibz.activitylogger.core.api;


public interface EditableLogRecord extends LogRecord {
    void setContextName(String name);
    void setContextId(String id);

    void setEntityName(String name);
    void setEntityId(String id);

    void setAction(String action);

    void addData(String key, Object value);
}
