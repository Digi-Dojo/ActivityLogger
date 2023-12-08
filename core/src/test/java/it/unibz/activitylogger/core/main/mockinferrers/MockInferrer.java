package it.unibz.activitylogger.core.main.mockinferrers;

import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.Inferrer;

public class MockInferrer implements Inferrer {
    protected Inferrer next;

    @Override
    public void setNext(Inferrer inferrer) {
        next = inferrer;
    }

    @Override
    public void infer(InferenceContext context) {
        if (next != null) {
            next.infer(context);
        }
    }

    public Inferrer getNext() {
        return next;
    }
}
