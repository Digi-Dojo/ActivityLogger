package it.unibz.activitylogger.core.business.utils;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Inferrer;

public class MockActionInferrer implements Inferrer {
    public static final String MOCK_ACTION = "mock";
    private Inferrer next;

    @Override
    public void setNext(Inferrer inferrer) {
        this.next = inferrer;
    }

    @Override
    public void infer(InferenceContext context) {
        context.getLogRecord().setAction(MOCK_ACTION);

        if (this.next != null)
            this.next.infer(context);
    }
}
