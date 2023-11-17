package it.unibz.activitylogger.core.business;

import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.InputProcessor;
import it.unibz.activitylogger.core.business.inference_context.InferenceContext;
import it.unibz.activitylogger.core.business.inferrer.Inferrer;
import it.unibz.activitylogger.core.business.inferrer.InferrerLoader;

public class BasicInputProcessor implements InputProcessor {
    private final InferrerLoader inferrerLoader;
    private final LogRecordRepository logRecordRepository;

    public BasicInputProcessor(InferrerLoader inferrerLoader, LogRecordRepository logRecordRepository) {
        this.inferrerLoader = inferrerLoader;
        this.logRecordRepository = logRecordRepository;
    }

    @Override
    public void process(Input input) {
        InferenceContext context = InferenceContext.createFrom(input);
        Inferrer chain = inferrerLoader.getChain();
        chain.infer(context);
        this.logRecordRepository.save(context.getLogRecord());
    }
}
