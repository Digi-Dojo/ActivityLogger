package it.unibz.activitylogger.core.api;

public interface Inferrer {
    void setNext(Inferrer inferrer);

    void infer(InferenceContext context);
}
