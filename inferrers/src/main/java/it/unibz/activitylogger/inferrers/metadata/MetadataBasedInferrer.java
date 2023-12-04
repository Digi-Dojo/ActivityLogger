package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.Metadata;
import it.unibz.activitylogger.inferrers.BaseInferrer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MetadataBasedInferrer extends BaseInferrer {
    protected static final String REFERENCE_REGEX = "^\\{(.+)@(.+)\\}$";
    protected Matcher matcher;

    protected abstract void inferFromMetadata(Metadata meta, Input input, EditableLogRecord logRecord);

    @Override
    public void infer(InferenceContext context) {
        Input input = context.readInput();

        if (input.hasMetadata()) {
            inferFromMetadata(input.meta(), input, context.getLogRecord());
        }

        safeCallNext(context);
    }

    protected boolean isReference(String raw) {
        matcher = Pattern
                .compile(REFERENCE_REGEX)
                .matcher(raw);

        return matcher.matches();
    }

    protected String extract(String ref, Input input) {
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

    protected String extractFromUri(String index, String uri) {
        int numericIndex = Integer.parseInt(index);
        List<String> parts = Arrays.stream(uri.split("/"))
                .filter(it -> !it.isBlank())
                .toList();

        return parts.get(numericIndex);
    }

    protected String extractFromBody(String index, Object body) {
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
