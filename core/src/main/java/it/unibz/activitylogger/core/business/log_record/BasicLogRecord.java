package it.unibz.activitylogger.core.business.log_record;

import java.util.HashMap;
import java.util.Map;

public class BasicLogRecord implements EditableLogRecord {

    private String contextName;
    private String contextId;
    private String entityName;
    private String entityId;
    private String action;
    private Map<String, Object> semiStructuredData;

    public BasicLogRecord() {
        contextName = "";
        contextId = "";
        entityName = "";
        entityId = "";
        action = "";
        semiStructuredData = new HashMap<>();
    }

    @Override
    public void setContextName(String name) {
        this.contextName = name;
    }

    @Override
    public void setContextId(String id) {
        this.contextId = id;
    }

    @Override
    public void setEntityName(String name) {
        this.entityName = name;
    }

    @Override
    public void setEntityId(String id) {
        this.entityId = id;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void addData(String key, Object value) {
        this.semiStructuredData.put(key, value);
    }

    @Override
    public String getContextName() {
        return this.contextName;
    }

    @Override
    public String getContextId() {
        return this.contextId;
    }

    @Override
    public String getEntityTypeName() {
        return this.entityName;
    }

    @Override
    public String getEntityId() {
        return this.entityId;
    }

    @Override
    public String getAction() {
        return this.action;
    }

    @Override
    public Object getSemiStructuredData() {
        return Map.copyOf(this.semiStructuredData);
    }

    @Override
    public String toString() {
        return "BasicLogRecord{" +
                "contextName='" + contextName + '\'' +
                ", contextId='" + contextId + '\'' +
                ", entityName='" + entityName + '\'' +
                ", entityId='" + entityId + '\'' +
                ", action='" + action + '\'' +
                ", semiStructuredData=" + semiStructuredData +
                '}';
    }
}
