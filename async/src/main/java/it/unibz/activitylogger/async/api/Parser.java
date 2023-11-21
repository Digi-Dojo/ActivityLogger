package it.unibz.activitylogger.async.api;

public interface Parser<T> {
    T from(String message);
}
