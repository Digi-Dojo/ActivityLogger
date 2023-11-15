package it.unibz.activitylogger.events;

import java.util.ArrayList;
import java.util.List;

public class GenericLogRecord extends LogRecord {

    public static LogRecord extract(Event event) {
        String action = event.getEventType();
        Long timestamp = event.getTimestamp();
        GenericLogRecord genericLogRecord = new GenericLogRecord(action, timestamp);

        List<String> keys = (List<String>) event.getStructureMetadata().get(Event.Header.ATTRIBUTES_KEYNAME);
        keys.forEach(key -> {
            String name = key;
            Object value = event.getBody().get(key);
            String type = (String) event.getStructureMetadata().get(key);

            genericLogRecord.addAdditionalData(name, value, type);
        });

        return genericLogRecord;
    }

    public static record AdditionalData(
            String name,
            Object value,
            String type
    ) {}

    public final String action;
    public final Long timestamp;
    private List<AdditionalData> additionalData;

    private GenericLogRecord(String action, Long timestamp) {
        this.action = action;
        this.timestamp = timestamp;
        this.additionalData = new ArrayList<>();
    }

    public void addAdditionalData(String name, Object value, String type) {
        this.additionalData.add(new AdditionalData(name, value, type));
    }

    public List<AdditionalData> getAdditionalData() {
        return List.copyOf(this.additionalData);
    }
}
