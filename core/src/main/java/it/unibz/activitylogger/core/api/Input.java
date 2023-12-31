package it.unibz.activitylogger.core.api;

import java.util.List;

public record Input(
        String method,
        String uri,
        Object body,
        Metadata meta
) {
    public static final String METHOD_POST = "post";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_GET = "get";
    public static final String METHOD_OPTIONS = "options";

    public Input(String method, String uri, Object body) {
        this(method, uri, body, null);
    }

    public boolean hasMetadata() {
        return this.meta != null;
    }

    public boolean isValid() {
        boolean anyInvalidNull = method == null || uri == null;

        if (anyInvalidNull)
            return false;

        return validMethod();
    }

    private boolean validMethod() {
        return List.of(
                METHOD_POST,
                METHOD_PUT,
                METHOD_GET,
                METHOD_OPTIONS
        ).contains(method);
    }
}
