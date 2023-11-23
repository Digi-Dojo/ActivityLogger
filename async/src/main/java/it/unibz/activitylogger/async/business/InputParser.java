package it.unibz.activitylogger.async.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unibz.activitylogger.async.api.InvalidInputMessageException;
import it.unibz.activitylogger.async.api.Parser;
import it.unibz.activitylogger.core.api.Input;

public class InputParser implements Parser<Input> {
    @Override
    public Input from(String message) {
        Gson gson = new GsonBuilder().create();

        Input input = gson.fromJson(message, Input.class);

        if (!input.isValid())
            throw new InvalidInputMessageException(message);

        return input;
    }
}
