package it.unibz.activitylogger;

import java.util.Map;

public record Event(
        EventHeader header,
        Map<String, Object> body
) {
}
