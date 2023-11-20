package it.unibz.activitylogger.core.business.inferrer;

import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.api.InferenceContext;

public abstract class BaseInferrer implements Inferrer {
    protected Inferrer next;

    @Override
    public void setNext(Inferrer inferrer) {
        this.next = inferrer;
    }

    protected final void safeCallNext(InferenceContext context) {
        if (hasNext())
            this.next.infer(context);
    }

    private boolean hasNext() {
        return this.next != null;
    }
}
