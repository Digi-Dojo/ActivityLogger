package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.InputProcessor;
import it.unibz.activitylogger.core.business.BasicInputProcessor;
import it.unibz.activitylogger.core.business.LogRecordRepository;
import it.unibz.activitylogger.core.business.inferrer.InferrerLoader;
import it.unibz.activitylogger.core.infrastructure.InMemoryLogRecordRepository;

public class ActivityLogger {
    public static void run(Input input) {
        InferrerLoader loader = new ManualLoader();
        LogRecordRepository repository = new InMemoryLogRecordRepository();

        InputProcessor processor = new BasicInputProcessor(loader, repository);

        processor.process(input);
    }

    private ActivityLogger() {}
}
