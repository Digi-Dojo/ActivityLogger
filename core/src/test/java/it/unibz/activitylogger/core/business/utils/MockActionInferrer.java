package it.unibz.activitylogger.core.business.utils;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.business.inferrer.BaseInferrer;

public class MockActionInferrer extends BaseInferrer {
    public static final String MOCK_ACTION = "mock";

    @Override
    public void infer(InferenceContext context) {
        context.getLogRecord().setAction(MOCK_ACTION);
    }
}
