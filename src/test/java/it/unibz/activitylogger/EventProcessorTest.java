package it.unibz.activitylogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class EventProcessorTest {
    private EventProcessor underTest;

    @BeforeEach
    void setup() {
        this.underTest = new EventProcessor();
    }

    @Test
    void givenTrivialEvent_thenGetsRecord() {
        // given
        String type = "User";
        String typeId = "1234-abcd";
        String action = "Updated";
        String fieldName = "email";
        String fieldValue = "fulano@mail.com";
        String fieldType = "String";
        Long timestamp = 1L;
        Event event = BasicEventBuilder.create()
                .theInstanceOf(type, typeId)
                .hasBeen(action)
                .withField(fieldName, fieldValue, fieldType)
                .at(timestamp)
                .build();

        // when
        List<LogRecord> processed = this.underTest.process(event);

        // then
        Assertions.assertEquals(2, processed.size());
        LogRecord logRecord = processed.get(0);
        Assertions.assertInstanceOf(LogRecord.class, logRecord);
        Assertions.assertEquals(type, logRecord.getClassType());
        Assertions.assertEquals(typeId, logRecord.getTypeId());
        Assertions.assertEquals(action, logRecord.getAction());
        Assertions.assertEquals(fieldName, logRecord.getField());
        Assertions.assertEquals(fieldValue, logRecord.getValue());
        Assertions.assertEquals(timestamp, logRecord.getTimestamp());
    }

    @Test
    void givenTwoFieldsUpdateEvent_thenGetsRecord() {
        // given
        String type = "User";
        String typeId = "1234-abcd";
        String action = "Updated";
        String oneFieldName = "email";
        String oneFieldValue = "fulano@mail.com";
        String oneFieldType = "String";
        String twoFieldName = "name";
        String twoFieldValue = "Fulano de Tal";
        String twoFieldType = "String";
        Long timestamp = 1L;
        Event event = BasicEventBuilder.create()
                .theInstanceOf(type, typeId)
                .hasBeen(action)
                .withField(oneFieldName, oneFieldValue, oneFieldType)
                .withField(twoFieldName, twoFieldValue, twoFieldType)
                .at(timestamp)
                .build();

        // when
        List<LogRecord> processed = this.underTest.process(event);

        // then
        Assertions.assertEquals(2, processed.size());

        LogRecord logRecord = processed.get(1);
        Assertions.assertInstanceOf(LogRecord.class, logRecord);
        Assertions.assertEquals(type, logRecord.getClassType());
        Assertions.assertEquals(typeId, logRecord.getTypeId());
        Assertions.assertEquals(action, logRecord.getAction());
        Assertions.assertEquals(oneFieldName, logRecord.getField());
        Assertions.assertEquals(oneFieldValue, logRecord.getValue());
        Assertions.assertEquals(timestamp, logRecord.getTimestamp());

        logRecord = processed.get(0);
        Assertions.assertEquals(type, logRecord.getClassType());
        Assertions.assertEquals(typeId, logRecord.getTypeId());
        Assertions.assertEquals(action, logRecord.getAction());
        Assertions.assertEquals(twoFieldName, logRecord.getField());
        Assertions.assertEquals(twoFieldValue, logRecord.getValue());
        Assertions.assertEquals(timestamp, logRecord.getTimestamp());
    }
}