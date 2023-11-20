package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Inferrer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionFromHttpVerbTest {
    private static void assertMethodMappedToAction(String givenMethod, String expectedAction) {
        // given
        Input input = new Input(givenMethod, "/hello/world", new Object());
        InferenceContext context = InferenceContext.createFrom(input);

        // when
        Inferrer inferrer = new ActionFromHttpVerb();
        inferrer.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        assertEquals(expectedAction, logRecord.getAction());
    }

    @Test
    void givenPOST_inferCreated() {
        assertMethodMappedToAction(
                Input.METHOD_POST,
                ActionFromHttpVerb.ACTION_CREATED
        );
    }

    @Test
    void givenPUT_inferUpdated() {
        assertMethodMappedToAction(
                Input.METHOD_PUT,
                ActionFromHttpVerb.ACTION_UPDATED
        );
    }

    @Test
    void givenGET_inferRead() {
        assertMethodMappedToAction(
                Input.METHOD_GET,
                ActionFromHttpVerb.ACTION_READ
        );
    }

    @Test
    void givenOPTIONS_inferUnknown() {
        assertMethodMappedToAction(
                Input.METHOD_OPTIONS,
                ActionFromHttpVerb.ACTION_UNKNOWN
        );
    }
}