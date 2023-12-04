package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.api.Metadata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ContextFromMetadataTest {
    @Test
    void whenGivenContextNameReferenceInMetadata_thenItIsStored() {
        // given
        String contextName = "my-context";
        String contextNameRef = "{uri@0}";
        Metadata metadata = Metadata.builder()
                .withContextType(contextNameRef)
                .build();
        Input input = new Input(Input.METHOD_PUT, "/" + contextName, null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ContextFromMetadata underTest = new ContextFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(contextName, logRecord.getContextName());
    }

    @Test
    void whenGivenContextKeyReferencesInMetadata_thenItIsStored() {
        // given
        String contextId = "123d-oliv-eira-0004";
        String contextIdRef = "{uri@1}";
        Metadata metadata = Metadata.builder()
                .withContextKey(contextIdRef)
                .build();
        Input input = new Input(Input.METHOD_PUT, "/random/" + contextId, null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ContextFromMetadata underTest = new ContextFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(contextId, logRecord.getContextId());
    }

    @Test
    void whenGivenContextKeyReferencingBody_thenItIsStored() {
        // given
        String contextId = "123d-oliv-eira-0004";
        String contextIdRef = "{body@foo.bar}";
        Metadata metadata = Metadata.builder()
                .withContextKey(contextIdRef)
                .build();

        Map<String, Map<String,String>> body = Map.of(
                "foo", Map.of("bar", contextId)
        );

        Input input = new Input(Input.METHOD_PUT, "/random/" + contextId, body, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ContextFromMetadata underTest = new ContextFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(contextId, logRecord.getContextId());
    }

    @Test
    void whenGivenFullContextReferenceInMetadata_thenItIsAllStored() {
        // given
        String contextName = "some-context";
        String contextId = "123d-oliv-eira-0004";
        String ctxNameRef = "{uri@0}";
        String ctxIdRef = "{uri@1}";
        String uri = "/%s/%s".formatted(contextName, contextId);
        Metadata metadata = Metadata.builder()
                .withContext(ctxNameRef, ctxIdRef)
                .build();
        Input input = new Input(Input.METHOD_PUT, uri, null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ContextFromMetadata underTest = new ContextFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(contextName, logRecord.getContextName());
        Assertions.assertEquals(contextId, logRecord.getContextId());
    }

    @Test
    void whenGivenFullContextReferenceMixedBodyAndUri_thenItIsAllStored() {
        // given
        String contextName = "some-context";
        String uri = "/%s".formatted(contextName);
        String ctxNameRef = "{uri@0}";

        String contextId = "123d-oliv-eira-0004";
        Map<String,Map<String,String>> body = Map.of("foo", Map.of("bar", contextId));
        String ctxIdRef = "{body@foo.bar}";

        Metadata metadata = Metadata.builder()
                .withContext(ctxNameRef, ctxIdRef)
                .build();

        Input input = new Input(Input.METHOD_PUT, uri, body, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ContextFromMetadata underTest = new ContextFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(contextName, logRecord.getContextName());
        Assertions.assertEquals(contextId, logRecord.getContextId());
    }

    @Test
    void whenGivenNoContextInMetadata_thenNothingIsStored() {
        // given
        Metadata metadata = Metadata.empty();
        Input input = new Input(Input.METHOD_PUT, "/random", null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        ContextFromMetadata underTest = new ContextFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertTrue(logRecord.getContextName().isBlank());
        Assertions.assertTrue(logRecord.getContextId().isBlank());
    }
}