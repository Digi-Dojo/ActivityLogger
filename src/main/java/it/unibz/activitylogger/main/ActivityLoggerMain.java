package it.unibz.activitylogger.main;

import it.unibz.activitylogger.core.api.Port;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ActivityLoggerMain {
    public static void main(String[] args) {
        System.out.println("running ActivityLoggerMain");
        Iterator<Port> portAdaptors = ServiceLoader.load(Port.class).iterator();

        while (portAdaptors.hasNext()) {
            Port adaptor = portAdaptors.next();
            System.out.println("loaded " + adaptor.getClass().getSimpleName());
            adaptor.run();
        }
    }
}