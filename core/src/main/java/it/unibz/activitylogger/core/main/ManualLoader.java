package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.business.inferrer.ActionFromHttpVerb;
import it.unibz.activitylogger.core.business.inferrer.Inferrer;
import it.unibz.activitylogger.core.business.inferrer.InferrerLoader;

import java.util.List;

public class ManualLoader implements InferrerLoader {
    @Override
    public Inferrer getChain() {
        List<Inferrer> inferrers = loadAll();
        link(inferrers);

        return inferrers.get(0);
    }

    private List<Inferrer> loadAll() {
        return List.of(
                new ActionFromHttpVerb()
        );
    }

    private void link(List<Inferrer> inferrers) {
        int first = 0;
        int last = inferrers.size() - 2;

        for (int i = first; i <= last; i++) {
            Inferrer current = inferrers.get(i);
            Inferrer next = inferrers.get(i + 1);
            current.setNext(next);
        }
    }
}
