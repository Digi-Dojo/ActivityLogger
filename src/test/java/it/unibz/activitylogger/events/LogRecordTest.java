package it.unibz.activitylogger.events;

import it.unibz.activitylogger.events.utils.EventBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LogRecordTest {

    @Nested
    class ExtractsFromUserEvent {
        private static Event generateUserEvent(String eventType, Long timestamp, String id) {
            return new EventBuilder()
                    .anInstanceOf("user")
                    .haveBeen(eventType)
                    .at(timestamp)
                    .withTheAttribute("id", id, "String")
                    .build();
        }

        private String eventType;
        private Long timestamp;
        private String id;
        private LogRecord record;

        @BeforeEach
        void setup() {
            // given
            eventType = "punched id";
            timestamp = 42L;
            id = "1234-abcd";
            Event event = ExtractsFromUserEvent.generateUserEvent(eventType, timestamp, id);

            // when
            record = LogRecord.extract(event);
        }

        @Test
        void isNotNull() {
            assertNotNull(record);
        }

        @Test
        void isInstanceOfUserLogRecord() {
            assertInstanceOf(UserLogRecord.class, record);
        }

        @Test
        void isCorrectUserLogRecord() {
            UserLogRecord userRecord = (UserLogRecord) record;
            assertEquals(id, userRecord.userId);
            assertEquals(eventType, userRecord.action);
            assertEquals(timestamp, userRecord.timestamp);
        }
    }

    @Nested
    class ExtractsFromUnknownEvent {
        private static String firstAttributeName = "something";
        private static Object firstAttributeValue = new Object();
        private static String firstAttributeType = "Object";
        private static String secondAttributeName = "other";
        private static Object secondAttributeValue = 123;
        private static String secondAttributeType = "Integer";

        private static Event generateUnknownEvent(String eventType, Long timestamp) {
            return new EventBuilder()
                    .anInstanceOf("whatever")
                    .haveBeen(eventType)
                    .at(timestamp)
                    .withTheAttribute(firstAttributeName, firstAttributeValue, firstAttributeType)
                    .and()
                    .withTheAttribute(secondAttributeName, secondAttributeValue, secondAttributeType)
                    .build();
        }

        private String eventType;
        private Long timestamp;
        private LogRecord record;

        @BeforeEach
        void setup() {
            // given
            eventType = "non existent";
            timestamp = 49389L;
            Event event = generateUnknownEvent(eventType, timestamp);

            // when
            record = LogRecord.extract(event);
        }

        @Test
        void isNotNull() {
            assertNotNull(record);
        }

        @Test
        void isInstanceOFfGenericLogRecord() {
            assertInstanceOf(GenericLogRecord.class, record);
        }

        @Test
        void isCorrectGenericLogRecord() {
            GenericLogRecord genericRecord = (GenericLogRecord) record;
            assertEquals(eventType, genericRecord.action);
            assertEquals(timestamp, genericRecord.timestamp);
        }

        @Test
        void attachesAdditionalInformation() {
            GenericLogRecord genericRecord = (GenericLogRecord) record;
            List<GenericLogRecord.AdditionalData> data = genericRecord.getAdditionalData();
            assertNotNull(data);
        }

        @Test
        void attachesCorrectAdditionalInformation() {
            GenericLogRecord.AdditionalData first = new GenericLogRecord.AdditionalData(
                    firstAttributeName,
                    firstAttributeValue,
                    firstAttributeType
            );
            GenericLogRecord.AdditionalData second = new GenericLogRecord.AdditionalData(
                    secondAttributeName,
                    secondAttributeValue,
                    secondAttributeType
            );
            GenericLogRecord genericRecord = (GenericLogRecord) record;
            List<GenericLogRecord.AdditionalData> data = genericRecord.getAdditionalData();
            assertEquals(2, data.size());
            assertEquals(first, data.get(1));
            assertEquals(second, data.get(0));
        }
    }
}