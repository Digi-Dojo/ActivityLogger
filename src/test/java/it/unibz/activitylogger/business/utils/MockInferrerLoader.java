package it.unibz.activitylogger.business.utils;

import it.unibz.activitylogger.business.inferrer.Inferrer;
import it.unibz.activitylogger.business.inferrer.InferrerLoader;

public class MockInferrerLoader implements InferrerLoader {
    @Override
    public Inferrer getChain() {
        return new MockActionInferrer();
    }
}
