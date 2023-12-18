package it.unibz.activitylogger.core.business;

import it.unibz.activitylogger.core.api.*;
import it.unibz.activitylogger.core.api.InferrerLoader;

public class BasicInputProcessor implements InputProcessor {
    private final InferrerLoader inferrerLoader;
    private final LogRecordSaver logRecordSaver;

    public BasicInputProcessor(InferrerLoader inferrerLoader, LogRecordSaver logRecordSaver) {
        this.inferrerLoader = inferrerLoader;
        this.logRecordSaver = logRecordSaver;
    }

    @Override
    public void process(Input input) {
        InferenceContext context = InferenceContext.createFrom(input);
        Inferrer chain = inferrerLoader.getChain();
        chain.infer(context);
        this.logRecordSaver.save(context.getLogRecord());
    }
}
