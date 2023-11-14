package it.unibz.activitylogger;

public interface EventBuilder {
    EventBuilder theInstanceOf(String classType, String id);

    EventBuilder hasBeen(String action);

    EventBuilder withField(String name, String value, String type);

    EventBuilder at(Long timestamp);

    Event build();
}
