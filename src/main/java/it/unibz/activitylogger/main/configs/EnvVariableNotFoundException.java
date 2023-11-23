package it.unibz.activitylogger.main.configs;

public class EnvVariableNotFoundException extends RuntimeException {
    public EnvVariableNotFoundException(String varName) {
        super("Env variable '%s' not found".formatted(varName));
    }
}
