package it.unibz.activitylogger;

import java.util.Map;
import java.util.Set;

public record EventHeader(
        Set<String> keys,
        Map<String, String> structure,
        String classType,
        String typeId,
        String action,
        Long timestamp
) {
}
