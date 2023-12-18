package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.InputProcessor;
import it.unibz.activitylogger.core.business.BasicInputProcessor;
import it.unibz.activitylogger.core.api.LogRecordSaver;
import it.unibz.activitylogger.core.api.InferrerLoader;
import it.unibz.activitylogger.core.infrastructure.InMemoryLogRecordRepository;

public class ActivityLogger {
    private final LogRecordSaver recordSaver;
    private final InferrerLoader loader;

    public ActivityLogger() {
        this.recordSaver = new InMemoryLogRecordRepository();
        this.loader = new AutomaticInferrerLoader();
    }

    public ActivityLogger(LogRecordSaver recordSaver) {
        this.recordSaver = recordSaver;
        this.loader = new AutomaticInferrerLoader();
    }

    public ActivityLogger(LogRecordSaver recordSaver, InferrerLoader loader) {
        this.recordSaver = recordSaver;
        this.loader = loader;
    }

    public void processInput(Input input) {
        InputProcessor processor = new BasicInputProcessor(this.loader, this.recordSaver);
        processor.process(input);
    }
}
