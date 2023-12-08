package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.InferenceTier;
import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.business.inferrer.InferrerLoader;

import java.util.*;

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
            System.out.println("loaded " + inferrer.getClass().getSimpleName());
        }

        return inferrers;
    }

    public void link(List<Inferrer> inferrers) {
        ArrayList<Inferrer> innerInferrers = new ArrayList<>(inferrers);
        innerInferrers.sort(comparatorByTier());

        int first = 0;
        int last = innerInferrers.size() - 2;

        for (int i = first; i <= last; i++) {
            Inferrer current = innerInferrers.get(i);
            Inferrer next = innerInferrers.get(i + 1);
            current.setNext(next);
        }
    }

    private Comparator<Inferrer> comparatorByTier() {
        return (infOne, infTwo) -> {
            Map<InferenceTier.Tier, Integer> mapper = tierMapper();

            InferenceTier.Tier tierOne = getTier(infOne);

            InferenceTier.Tier tierTwo = getTier(infTwo);


            return mapper.get(tierOne) - mapper.get(tierTwo);
        };
    }

    private Map<InferenceTier.Tier, Integer> tierMapper() {
        return Map.of(
                InferenceTier.Tier.LOW, 1,
                InferenceTier.Tier.MEDIUM, 2,
                InferenceTier.Tier.HIGH, 3
        );
    }

    private InferenceTier.Tier getTier(Inferrer infTwo) {
        InferenceTier annotation = infTwo
                .getClass()
                .getAnnotation(InferenceTier.class);

        if (annotation != null)
            return annotation.value();

        return InferenceTier.Tier.LOW;
    }
}
