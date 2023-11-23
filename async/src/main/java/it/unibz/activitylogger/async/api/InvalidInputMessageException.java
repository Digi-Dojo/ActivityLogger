package it.unibz.activitylogger.async.api;

public class InvalidInputMessageException extends RuntimeException {
    public InvalidInputMessageException(String rawJson) {
        super("Input message invalid: " + rawJson);
    }
}
