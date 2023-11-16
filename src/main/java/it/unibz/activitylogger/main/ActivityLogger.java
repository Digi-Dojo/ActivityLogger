package it.unibz.activitylogger.main;

import it.unibz.activitylogger.api.Input;
import it.unibz.activitylogger.api.InputProcessor;
import it.unibz.activitylogger.business.BasicInputProcessor;
import it.unibz.activitylogger.business.LogRecordRepository;
import it.unibz.activitylogger.business.inferrer.InferrerLoader;
import it.unibz.activitylogger.infrastructure.InMemoryLogRecordRepository;

public class ActivityLogger {
    public static void run(Input input) {
        InferrerLoader loader = new ManualLoader();
        LogRecordRepository repository = new InMemoryLogRecordRepository();

        InputProcessor processor = new BasicInputProcessor(loader, repository);

        processor.process(input);
    }

    private ActivityLogger() {}
}
