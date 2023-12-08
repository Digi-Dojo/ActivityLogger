package it.unibz.activitylogger.core.main;

import it.unibz.activitylogger.core.api.Inferrer;
import it.unibz.activitylogger.core.main.mockinferrers.High;
import it.unibz.activitylogger.core.main.mockinferrers.Low;
import it.unibz.activitylogger.core.main.mockinferrers.Medium;
import it.unibz.activitylogger.core.main.mockinferrers.Unmarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AutomaticInferrerLoaderTest {
    @Test
    void whenGivenTwoInferrers_thenItLinksRespectingTier() {
        // given
        Low low = new Low();
        High high = new High();
        List<Inferrer> inferrers = List.of(
                high,
                low
        );
        AutomaticInferrerLoader underTest = new AutomaticInferrerLoader();

        // when
        underTest.link(inferrers);

        // then
        Assertions.assertEquals(high, low.getNext());
    }

    @Test
    void whenGivenThreeInferrers_thenItLinksRespectingTier() {
        // given
        Low low = new Low();
        Medium medium = new Medium();
        High high = new High();
        List<Inferrer> inferrers = List.of(
                high,
                low,
                medium
        );
        AutomaticInferrerLoader underTest = new AutomaticInferrerLoader();

        // when
        underTest.link(inferrers);

        // then
        Assertions.assertEquals(medium, low.getNext());
        Assertions.assertEquals(high, medium.getNext());
    }

    @Test
    void whenGivenAnUnmarkedInferrer_thenItDoesntThrow() {
        // given
        Unmarked unmarked = new Unmarked();
        Low low = new Low();
        List<Inferrer> inferrers = List.of(unmarked, low);
        AutomaticInferrerLoader underTest = new AutomaticInferrerLoader();

        // when ... then
        Assertions.assertDoesNotThrow(() -> underTest.link(inferrers));
    }
}