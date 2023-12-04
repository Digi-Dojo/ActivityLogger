package it.unibz.activitylogger.inferrers.metadata;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.api.Metadata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class EntityFromMetadataTest {
    @Test
    void whenGivenEntityNameReferenceInMetadata_thenItIsStored() {
        // given
        String entityName = "my-entity";
        String entityNameRef = "{uri@0}";
        Metadata metadata = Metadata.builder()
                .withEntityType(entityNameRef)
                .build();
        Input input = new Input(Input.METHOD_PUT, "/" + entityName, null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        EntityFromMetadata underTest = new EntityFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(entityName, logRecord.getEntityTypeName());
    }

    @Test
    void whenGivenEntityKeyReferencesInMetadata_thenItIsStored() {
        // given
        String entityId = "123d-oliv-eira-0004";
        String entityIdRef = "{uri@1}";
        Metadata metadata = Metadata.builder()
                .withEntityKey(entityIdRef)
                .build();
        Input input = new Input(Input.METHOD_PUT, "/random/" + entityId, null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        EntityFromMetadata underTest = new EntityFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(entityId, logRecord.getEntityId());
    }

    @Test
    void whenGivenEntityKeyReferencingBody_thenItIsStored() {
        // given
        String entityId = "123d-oliv-eira-0004";
        String entityIdRef = "{body@foo.bar}";
        Metadata metadata = Metadata.builder()
                .withEntityKey(entityIdRef)
                .build();

        Map<String, Map<String,String>> body = Map.of(
                "foo", Map.of("bar", entityId)
        );

        Input input = new Input(Input.METHOD_PUT, "/random/" + entityId, body, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        EntityFromMetadata underTest = new EntityFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(entityId, logRecord.getEntityId());
    }

    @Test
    void whenGivenFullEntityReferenceInMetadata_thenItIsAllStored() {
        // given
        String entityName = "some-entity";
        String entityId = "123d-oliv-eira-0004";
        String entityNameRef = "{uri@0}";
        String entityIdRef = "{uri@1}";
        String uri = "/%s/%s".formatted(entityName, entityId);
        Metadata metadata = Metadata.builder()
                .withEntity(entityNameRef, entityIdRef)
                .build();
        Input input = new Input(Input.METHOD_PUT, uri, null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        EntityFromMetadata underTest = new EntityFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(entityName, logRecord.getEntityTypeName());
        Assertions.assertEquals(entityId, logRecord.getEntityId());
    }

    @Test
    void whenGivenFullEntityReferenceMixedBodyAndUri_thenItIsAllStored() {
        // given
        String entityName = "some-context";
        String uri = "/%s".formatted(entityName);
        String entityNameRef = "{uri@0}";

        String entityId = "123d-oliv-eira-0004";
        Map<String,Map<String,String>> body = Map.of("foo", Map.of("bar", entityId));
        String entityIdRef = "{body@foo.bar}";

        Metadata metadata = Metadata.builder()
                .withEntity(entityNameRef, entityIdRef)
                .build();

        Input input = new Input(Input.METHOD_PUT, uri, body, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        EntityFromMetadata underTest = new EntityFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertEquals(entityName, logRecord.getEntityTypeName());
        Assertions.assertEquals(entityId, logRecord.getEntityId());
    }

    @Test
    void whenGivenNoEntityInMetadata_thenNothingIsStored() {
        // given
        Metadata metadata = Metadata.empty();
        Input input = new Input(Input.METHOD_PUT, "/random", null, metadata);
        InferenceContext context = InferenceContext.createFrom(input);
        EntityFromMetadata underTest = new EntityFromMetadata();

        // when
        underTest.infer(context);

        // then
        LogRecord logRecord = context.getLogRecord();
        Assertions.assertTrue(logRecord.getEntityTypeName().isBlank());
        Assertions.assertTrue(logRecord.getEntityId().isBlank());
    }
}