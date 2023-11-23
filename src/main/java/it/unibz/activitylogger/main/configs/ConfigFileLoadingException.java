package it.unibz.activitylogger.main.configs;

import java.io.IOException;

public class ConfigFileLoadingException extends RuntimeException {
    public ConfigFileLoadingException(IOException ioException) {
        super("Failed loading '" + ConfigLoader.CONFIG_FILE_NAME + "' file", ioException);
    }
}
