package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.Metadata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ContextFromMetadata extends MetadataBasedInferrer {
    @Override
    protected void inferFromMetadata(Metadata meta, Input input, EditableLogRecord logRecord) {
        String idRef = meta.contextKey();
        String nameRef = meta.contextType();

        if (idRef != null) {
            String id = extract(idRef, input);
            logRecord.setContextId(id);
        }

        if (nameRef != null) {
            String contextName = extract(nameRef, input);
            logRecord.setContextName(contextName);
        }
    }

    private boolean isReference(String raw) {
        matcher = Pattern
                .compile(REFERENCE_REGEX)
                .matcher(raw);

        return matcher.matches();
    }

    private String extract(String ref, Input input) {
        if (!isReference(ref)) {
            return ref;
        }

        String target = matcher.group(1);
        String index = matcher.group(2);

        return switch (target) {
            case "uri" -> extractFromUri(index, input.uri());
            case "body" -> extractFromBody(index, input.body());
            default -> throw new IllegalStateException("Unexpected value: " + target);
        };
    }

    private String extractFromUri(String index, String uri) {
        int numericIndex = Integer.parseInt(index);
        List<String> parts = Arrays.stream(uri.split("/"))
                .filter(it -> !it.isBlank())
                .toList();

        return parts.get(numericIndex);
    }

    private String extractFromBody(String index, Object body) {
        String[] keys = index.split("\\.");

        Map<?, ?> currentMap = (Map<?,?>) body;
        Object result = null;

        for (String key : keys) {
            if (currentMap.containsKey(key)) {
                result = currentMap.get(key);

                if (result instanceof Map) {
                    currentMap = (Map<String, Object>) result;
                } else {
                    break;
                }
            } else {
                result = null;
                break;
            }
        }

        return (String) result;
    }
}
