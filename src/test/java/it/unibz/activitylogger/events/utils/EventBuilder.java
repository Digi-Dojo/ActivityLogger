package it.unibz.activitylogger.events.utils;

import it.unibz.activitylogger.events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class EventBuilder {
    private String sourceType;
    private String eventType;
    private Map<String, Object> structure;
    private Long timestamp;
    private Map<String, Object> body;

    private void clear() {
        this.sourceType = null;
        this.eventType = null;
        this.structure = new HashMap<>();
        this.timestamp = null;
        this.body = new HashMap<>();
    }

    public EventBuilder() {
        this.clear();
    }

    public EventBuilder anInstanceOf(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public EventBuilder haveBeen(String eventType) {
        this.eventType = eventType;

        return this;
    }

    public EventBuilder at(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public EventBuilder withTheAttribute(String name, Object value, String type) {
        this.structure.put(name, type);
        this.body.put(name, value);

        return this;
    }

    public EventBuilder and() {
        return this;
    }

    public Event build() {
        Event event = new Event();

        Event.Header header = this.buildHeader();

        event.setBody(this.body);
        event.setHeader(header);

        return event;
    }

    private Event.Header buildHeader() {
        Event.Header header = new Event.Header(
                this.eventType,
                this.sourceType,
                this.timestamp
        );

        String attributesKeyname = Event.Header.ATTRIBUTES_KEYNAME;
        List<String> attributes = this.structure.keySet()
                .stream()
                .filter(it -> !it.equals(attributesKeyname))
                .toList();
        this.structure.put(attributesKeyname, attributes);

        header.setStructure(this.structure);

        return header;
    }
}
