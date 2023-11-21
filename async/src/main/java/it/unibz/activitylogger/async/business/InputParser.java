package it.unibz.activitylogger.async.business;

import it.unibz.activitylogger.async.api.Parser;
import it.unibz.activitylogger.core.api.Input;

public class InputParser implements Parser<Input> {
    @Override
    public Input from(String message) {
        return new Input("", "", new Object());
    }
}
