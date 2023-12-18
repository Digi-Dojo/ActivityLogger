package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.InferrerLoader;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.InputProcessor;
import it.unibz.activitylogger.core.api.LogRecordSaver;
import it.unibz.activitylogger.core.business.BasicInputProcessor;

public class ActivityLogger {
    private LogRecordSaver recordSaver;
    private final InferrerLoader loader;

    public ActivityLogger() {
        this.loader = new AutomaticInferrerLoader();
    }

    public ActivityLogger(InferrerLoader loader) {
        this.loader = loader;
    }

    public void setRecordSaver(LogRecordSaver logRecordSaver) {
        this.recordSaver = logRecordSaver;
    }

    public void processInput(Input input) {
        if (this.recordSaver == null)
            throw new SaverNotDefinedException();

        InputProcessor processor = new BasicInputProcessor(this.loader, this.recordSaver);
        processor.process(input);
    }
}
