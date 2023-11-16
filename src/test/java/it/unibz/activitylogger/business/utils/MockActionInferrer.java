package it.unibz.activitylogger.business.utils;

import it.unibz.activitylogger.business.inference_context.InferenceContext;
import it.unibz.activitylogger.business.inferrer.BaseInferrer;

public class MockActionInferrer extends BaseInferrer {
    public static final String MOCK_ACTION = "mock";

    @Override
    public void infer(InferenceContext context) {
        context.getLogRecord().setAction(MOCK_ACTION);
    }
}
