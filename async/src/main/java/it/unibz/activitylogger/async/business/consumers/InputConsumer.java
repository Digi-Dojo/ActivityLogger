package it.unibz.activitylogger.async.business.consumers;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.main.ActivityLogger;

import it.unibz.activitylogger.async.api.Callback;
import it.unibz.activitylogger.async.api.Parser;


public class InputConsumer implements Callback {
    private final Parser<Input> inputParser;

    public InputConsumer(Parser<Input> inputParser) {
        this.inputParser = inputParser;
    }

    @Override
    public void run(String message) {
        Input input = inputParser.from(message);
        ActivityLogger.process(input);
    }
}
