package it.unibz.activitylogger.core.business.inferrer;

import it.unibz.activitylogger.core.business.inference_context.InferenceContext;

public interface Inferrer {
    void setNext(Inferrer inferrer);

    void infer(InferenceContext context);
}
