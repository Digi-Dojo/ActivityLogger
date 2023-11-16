package it.unibz.activitylogger.business.inferrer;

import it.unibz.activitylogger.business.inference_context.InferenceContext;

public interface Inferrer {
    void setNext(Inferrer inferrer);

    void infer(InferenceContext context);
}
