package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.business.inferrer.InferrerLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class AutomaticInferrerLoader implements InferrerLoader {
    @Override
    public Inferrer getChain() {
        List<Inferrer> inferrers = loadAll();
        link(inferrers);

        return inferrers.get(0);
    }

    private List<Inferrer> loadAll() {
        ServiceLoader<Inferrer> inferrerLoader = ServiceLoader.load(Inferrer.class);

        List<Inferrer> inferrers = new ArrayList<>();
        Iterator<Inferrer> iterator = inferrerLoader.iterator();
        while (iterator.hasNext()) {
            Inferrer inferrer = iterator.next();
            inferrers.add(inferrer);
        }

        return inferrers;
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
