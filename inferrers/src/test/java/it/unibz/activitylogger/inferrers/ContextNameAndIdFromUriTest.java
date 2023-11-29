package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContextNameAndIdFromUriTest {
    @Test
    void whenGivenPathWithIdentifiedNamespace_thenContextNameIsDefined() {
        // given
        String namespace = "context";
        Integer id = 42;
        Input input = new Input(Input.METHOD_POST, "/%s/%d/whatever".formatted(namespace, id), new Object());
        InferenceContext context = InferenceContext.createFrom(input);
        ContextNameAndIdFromUri underTest = new ContextNameAndIdFromUri();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(namespace, logRecord.getContextName());
        Assertions.assertEquals(Integer.toString(id), logRecord.getContextId());
    }

    @Test
    void whenGivenPathWithoutIdentifiedNamespace_thenNothingIsDefined() {
        // given
        Input input = new Input(Input.METHOD_POST, "/whatever", new Object());
        InferenceContext context = InferenceContext.createFrom(input);
        ContextNameAndIdFromUri underTest = new ContextNameAndIdFromUri();
        EditableLogRecord beforeRecord = context.getLogRecord();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(beforeRecord.getContextName(), logRecord.getContextName());
        Assertions.assertEquals(beforeRecord.getContextId(), logRecord.getContextId());
    }
}