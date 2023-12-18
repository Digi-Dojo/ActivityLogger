package it.unibz.activitylogger.main;

import it.unibz.activitylogger.core.api.LogRecordSaver;
import it.unibz.activitylogger.core.api.Port;
import it.unibz.activitylogger.main.configs.ConfigLoader;
import it.unibz.activitylogger.persistence.api.DefaultImplementation;
import it.unibz.activitylogger.persistence.api.PreferableImplementation;

import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

public class ActivityLoggerMain {

    public static void main(String[] args) {
        Properties configs = loadConfigs();
        LogRecordSaver selectedSaver = selectSaver();
        Iterator<Port> portAdaptors = loadPorts();
        runPortAdaptors(portAdaptors, selectedSaver, configs);
    }

    private static Properties loadConfigs() {
        ConfigLoader configLoader = new ConfigLoader();
        Properties configs = configLoader.loadConfiguration();

        System.out.println("running ActivityLoggerMain");
        System.out.println(configs);

        return configs;
    }

    private static LogRecordSaver selectSaver() {
        Iterator<LogRecordSaver> logRecordSaverIterator = loadSavers();

        LogRecordSaver defaultSaver = null;

        while (logRecordSaverIterator.hasNext()) {
            LogRecordSaver saver = logRecordSaverIterator.next();
            boolean isPreferable = saver.getClass().isAnnotationPresent(PreferableImplementation.class);

            if (isPreferable)
                return saver;

            boolean isDefault = saver.getClass().isAnnotationPresent(DefaultImplementation.class);

            if (isDefault)
                defaultSaver = saver;
        }

        return defaultSaver;
    }

    private static Iterator<LogRecordSaver> loadSavers() {
        return ServiceLoader.load(LogRecordSaver.class).iterator();
    }

    private static Iterator<Port> loadPorts() {
        return ServiceLoader.load(Port.class).iterator();
    }

    private static void runPortAdaptors(Iterator<Port> portAdaptors, LogRecordSaver selectedSaver, Properties configs) {
        while (portAdaptors.hasNext()) {
            Port adaptor = portAdaptors.next();
            System.out.println("loaded " + adaptor.getClass().getSimpleName());
            adaptor.setLogRecordSaver(selectedSaver);
            adaptor.run(configs);
        }
    }


}