package it.unibz.activitylogger.main;

import it.unibz.activitylogger.core.api.Port;
import it.unibz.activitylogger.main.configs.ConfigLoader;

import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

public class ActivityLoggerMain {

    public static void main(String[] args) {
        ConfigLoader configLoader = new ConfigLoader();
        Properties configs = configLoader.loadConfiguration();

        System.out.println("running ActivityLoggerMain");
        System.out.println(configs);

        Iterator<Port> portAdaptors = ServiceLoader.load(Port.class).iterator();

        while (portAdaptors.hasNext()) {
            Port adaptor = portAdaptors.next();
            System.out.println("loaded " + adaptor.getClass().getSimpleName());
            adaptor.run(configs);
        }
    }
}