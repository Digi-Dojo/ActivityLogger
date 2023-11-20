package it.unibz.activitylogger.core.business.inferrer;

import it.unibz.activitylogger.core.api.Inferrer;

public interface InferrerLoader {
    Inferrer getChain();
}
