package it.unibz.activitylogger.async.business.consumers;

import it.unibz.activitylogger.async.api.InvalidInputMessageException;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.main.ActivityLogger;

import it.unibz.activitylogger.async.api.Callback;
import it.unibz.activitylogger.async.api.Parser;


public class InputConsumer implements Callback {
    private final Parser<Input> inputParser;
    private final ActivityLogger activityLogger;

    public InputConsumer(Parser<Input> inputParser) {
        this.inputParser = inputParser;
        this.activityLogger = new ActivityLogger();
    }

    @Override
    public void run(String message) {
        try {
            Input input = inputParser.from(message);
            activityLogger.processInput(input);
        } catch (InvalidInputMessageException iime) {
            System.out.println(iime.getLocalizedMessage());
        }
    }
}
