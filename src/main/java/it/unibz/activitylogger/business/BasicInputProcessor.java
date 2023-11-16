package it.unibz.activitylogger.business;

import it.unibz.activitylogger.api.Input;
import it.unibz.activitylogger.business.inference_context.InferenceContext;
import it.unibz.activitylogger.business.inferrer.Inferrer;
import it.unibz.activitylogger.business.inferrer.InferrerLoader;

public class BasicInputProcessor implements it.unibz.activitylogger.api.InputProcessor {
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
