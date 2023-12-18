package it.unibz.activitylogger.core.main.mockinferrerloaders;

import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.api.InferrerLoader;
import it.unibz.activitylogger.core.main.mockinferrers.MockInferrer;

public class MockInferrerLoader implements InferrerLoader {
    @Override
    public Inferrer getChain() {
        return new MockInferrer();
    }
}
