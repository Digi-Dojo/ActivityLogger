package it.unibz.activitylogger.core.main;

public class SaverNotDefinedException extends RuntimeException {
    public SaverNotDefinedException() {
        super("LogRecordSaver not defined when calling ActivityLogger::processInput");
    }
}
