package it.unibz.activitylogger.core.main.mockinputs;

import it.unibz.activitylogger.core.api.Input;

public class NullInputFactory {
    public static Input newInput() {
        return new Input("", "", null);
    }
}