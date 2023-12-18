package it.unibz.activitylogger.core.business;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecord;
import it.unibz.activitylogger.core.api.InferrerLoader;
import it.unibz.activitylogger.core.business.utils.MockActionInferrer;
import it.unibz.activitylogger.core.business.utils.ParameterCatcher;
import it.unibz.activitylogger.core.business.utils.MockInferrerLoader;
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