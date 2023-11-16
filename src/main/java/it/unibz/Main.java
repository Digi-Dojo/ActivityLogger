package it.unibz;

import it.unibz.activitylogger.api.Input;
import it.unibz.activitylogger.main.ActivityLogger;

public class Main {
    public static void main(String[] args) {
        ActivityLogger.run(new Input(Input.METHOD_POST, "/hello/world", new Object()));
    }
}