package it.unibz.activitylogger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BasicEventBuilder implements EventBuilder {
    private Set<String> keys;
    private Map<String, String> structure;
    private String classType;
    private String typeId;
    private String action;
    private Long timestamp;
    private Map<String, Object> body;

    public static EventBuilder create() {
        return new BasicEventBuilder();
    }

    private BasicEventBuilder() {
        this.clear();
    }

    private void clear() {
        this.keys = new HashSet<>();
        this.structure = new HashMap<>();
        this.classType = null;
        this.typeId = null;
        this.action = null;
        this.timestamp = null;
        this.body = new HashMap<>();
    }

    @Override
    public EventBuilder theInstanceOf(String classType, String id) {
        this.classType = classType;
        this.typeId = id;
        return this;
    }

    @Override
    public EventBuilder hasBeen(String action) {
        this.action = action;
        return this;
    }

    @Override
    public EventBuilder withField(String name, String value, String type) {
        this.keys.add(name);
        this.structure.put(name, type);
        this.body.put(name, value);
        return this;
    }

    @Override
    public EventBuilder at(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public Event build() {
        EventHeader header = this.buildHeader();

        Event event = new Event(header, this.body);

        this.clear();

        return event;
    }

    private EventHeader buildHeader() {
        return new EventHeader(
                this.keys,
                this.structure,
                this.classType,
                this.typeId,
                this.action,
                this.timestamp
        );
    }
}
