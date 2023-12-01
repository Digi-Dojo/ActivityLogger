package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.api.Metadata;
import it.unibz.activitylogger.inferrers.metadata.ActionFromMetadata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionFromMetadataTest {
    @Test
    void whenGivenActionInMetadata_thenItIsStored() {
        // given
        String givenAction = "whatever action";
        Metadata metadata = Metadata.builder()
                .withAction(givenAction)
                .build();
        Input input = new Input(Input.METHOD_POST, "/something", null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ActionFromMetadata underTest = new ActionFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(givenAction, logRecord.getAction());
    }

    @Test
    void whenGivenMetadataWithoutAction_thenNothingIsStored() {
        // given
        Metadata metadata = Metadata.empty();
        Input input = new Input(Input.METHOD_PUT, "/some_url", null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ActionFromMetadata underTest = new ActionFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertTrue(logRecord.getAction().isBlank());
    }
}