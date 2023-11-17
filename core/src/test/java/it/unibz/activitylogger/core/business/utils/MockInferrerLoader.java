package it.unibz.activitylogger.core.business.utils;

import it.unibz.activitylogger.core.business.inferrer.Inferrer;
import it.unibz.activitylogger.core.business.inferrer.InferrerLoader;

public class MockInferrerLoader implements InferrerLoader {
    @Override
    public Inferrer getChain() {
        return new MockActionInferrer();
    }
}
