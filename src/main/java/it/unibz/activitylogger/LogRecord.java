package it.unibz.activitylogger;

public class LogRecord {
    private String classType;
    private String typeId;
    private String action;
    private String field;
    private Object value;
    private Long timestamp;

    public LogRecord() {
    }

    public LogRecord(String classType, String typeId, String action, String field, Object value, Long timestamp) {
        this.classType = classType;
        this.typeId = typeId;
        this.action = action;
        this.field = field;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getClassType() {
        return classType;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getAction() {
        return action;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
