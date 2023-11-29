package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ActionInUriTest {
    @Test
    void whenGivenTermEndingPathUnrelatedToBody_thenItsTakenAsAction() {
        // given
        String verbInPath = "punch-in";
        Input input = new Input("POST", "/startups/42/users/13/" + verbInPath, new Object());
        InferenceContext context = InferenceContext.createFrom(input);
        ActionInUri underTest = new ActionInUri();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(verbInPath, logRecord.getAction());
    }

    @Test
    void whenGivenTermEndingPathRelatedToBody_thenNothingIsTaken() {
        // given
        String resourceName = "users";
        String resourceKey = "user";
        Map<String, Object> body = new HashMap<>();
        body.put(resourceKey, new Object());
        Input input = new Input("POST", "/startups/42/" + resourceName, body);
        InferenceContext context = InferenceContext.createFrom(input);
        ActionInUri underTest = new ActionInUri();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        String action = logRecord.getAction();
        Assertions.assertNotEquals(resourceName, action);
        Assertions.assertNotEquals(resourceKey, action);
    }
}