package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.main.mockinferrerloaders.MockInferrerLoader;
import it.unibz.activitylogger.core.main.mockinputs.NullInputFactory;
import it.unibz.activitylogger.core.main.mocksavers.MockSaver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivityLoggerTest {
    private ActivityLogger underTest;

    @BeforeEach
    void setup() {
        this.underTest = new ActivityLogger(new MockInferrerLoader());
    }

    @Test
    void givenNullAsSaver_thenThrowsWhenCallingProcessInput() {
        // given
        Input mockInput = NullInputFactory.newInput();

        // when ... then
        Assertions.assertThrows(
                SaverNotDefinedException.class,
                () -> underTest.processInput(mockInput)
        );
    }

    @Test
    void givenNotNullSaver_thenDoesntThrow() {
        // given
        Input mockInput = NullInputFactory.newInput();
        underTest.setRecordSaver(new MockSaver());

        // when ... then
        Assertions.assertDoesNotThrow(
                () -> underTest.processInput(mockInput)
        );
    }
}