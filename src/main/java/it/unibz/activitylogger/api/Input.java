package it.unibz.activitylogger.api;

public record Input(
        String method,
        String uri,
        Object body
) {
    public static final String METHOD_POST = "post";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_GET = "get";
    public static final String METHOD_OPTIONS = "options";
}
