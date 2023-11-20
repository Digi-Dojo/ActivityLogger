package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.InputProcessor;
import it.unibz.activitylogger.core.business.BasicInputProcessor;
import it.unibz.activitylogger.core.business.LogRecordSaver;
import it.unibz.activitylogger.core.business.inferrer.InferrerLoader;
import it.unibz.activitylogger.core.infrastructure.InMemoryLogRecordRepository;

public class ActivityLogger {
    private static InMemoryLogRecordRepository getRepository() {
        InMemoryLogRecordRepository inMemory = new InMemoryLogRecordRepository();

        return new LoggerProxyRepository(inMemory);
    }

    public static void process(Input input) {
        InferrerLoader loader = new AutomaticInferrerLoader();
        LogRecordSaver repository = getRepository();

        InputProcessor processor = new BasicInputProcessor(loader, repository);

        processor.process(input);
    }

    private ActivityLogger() {}
}
