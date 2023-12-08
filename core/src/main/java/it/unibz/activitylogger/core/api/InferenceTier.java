package it.unibz.activitylogger.core.api;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InferenceTier {
    enum Tier { LOW, MEDIUM, HIGH }

    Tier value();
}
