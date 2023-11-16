package it.unibz.activitylogger.business;

import it.unibz.activitylogger.api.Input;
import it.unibz.activitylogger.api.LogRecord;
import it.unibz.activitylogger.business.inferrer.InferrerLoader;
import it.unibz.activitylogger.business.utils.MockActionInferrer;
import it.unibz.activitylogger.business.utils.ParameterCatcher;
import it.unibz.activitylogger.business.utils.MockInferrerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicInputProcessorTest {
    private ParameterCatcher repository;
    private BasicInputProcessor underTest;

    @BeforeEach
    void setup() {
        // given
        InferrerLoader loader = new MockInferrerLoader();
        repository = new ParameterCatcher();
        underTest = new BasicInputProcessor(loader, repository);
        Input input = new Input(Input.METHOD_POST, "/hello/world", new Object());

        // when
        underTest.process(input);
    }

    @Test
    void savesSimpleRecord() {
        assertTrue(repository.saveHaveBeenCalled());
    }

    @Test
    void generatesCorrectRecord() {
        LogRecord storedRecord = repository.getGivenLogRecord();
        assertEquals(MockActionInferrer.MOCK_ACTION, storedRecord.getAction());
    }
}