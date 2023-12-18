package it.unibz.activitylogger.core.business.utils;

import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.api.InferrerLoader;

public class MockInferrerLoader implements InferrerLoader {
    @Override
    public Inferrer getChain() {
        return new MockActionInferrer();
    }
}
