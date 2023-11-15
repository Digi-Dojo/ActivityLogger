package it.unibz.activitylogger.events;

import java.util.HashMap;
import java.util.Map;

public class Event {
    public static class Header {
        public static final String ATTRIBUTES_KEYNAME = "attributes";

        private final String eventType;
        private final String sourceType;
        private final Long timestamp;
        private final Map<String, Object> structure;

        public Header(String eventType, String sourceType, Long timestamp) {
            this.eventType = eventType;
            this.sourceType = sourceType;
            this.timestamp = timestamp;
            this.structure = new HashMap<>();
        }

        public String getEventType() {
            return eventType;
        }

        public String getSourceType() {
            return sourceType;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setStructure(Map<String, Object> structure) {
            this.structure.putAll(structure);
        }

        public Map<String, Object> getStructure() {
            return Map.copyOf(this.structure);
        }

        @Override
        public String toString() {
            return "eventType='" + eventType + '\'' +
                    ", sourceType='" + sourceType + '\'' +
                    ", timestamp=" + timestamp +
                    ", structure=" + structure;
        }
    }

    private Header header;
    private Map<String, Object> body;

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Header getHeader() {
        return this.header;
    }

    public Map<String, Object> getBody() {
        return Map.copyOf(this.body);
    }

    public String getEventType() {
        return this.header.getEventType();
    }

    public String getSourceType() {
        return this.header.getSourceType();
    }

    public Long getTimestamp() {
        return this.header.getTimestamp();
    }

    public Map<String, Object> getStructureMetadata() {
        return this.header.getStructure();
    }

    @Override
    public String toString() {
        return "Event\n" +
                "\theader: " + this.header.toString() + "\n" +
                "\tbody: " + this.body.toString();
    }
}
