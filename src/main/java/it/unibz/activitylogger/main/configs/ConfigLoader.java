package it.unibz.activitylogger.main.configs;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigLoader {
    public static final String CONFIG_FILE_NAME = "application.properties";

    public static String indicateAsEnvVar(String varName) {
        return "${%s}".formatted(varName);
    }

    public Properties loadConfiguration() {
        Properties configs = readPropertiesFile();

        Map<String, String> env = System.getenv();

        return mergeEnvIntoConfigs(env, configs);
    }

    public Properties readPropertiesFile() {
        Properties configs = new Properties();
        InputStream appProps = this.getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);

        try {
            configs.load(appProps);
            appProps.close();
            return configs;
        } catch (IOException e) {
            throw new ConfigFileLoadingException(e);
        }
    }

    public Properties mergeEnvIntoConfigs(Map<String, String> env, Properties file) {
        HashMap<String, String> mutableEnv = new HashMap<>();

        env.forEach((key, value) -> mutableEnv.put(indicateAsEnvVar(key), value));

        file.keySet().forEach(key -> {
            String value = (String) file.get(key);

            if (!isEnvVariableName(value))
                return;

            String envVar = mutableEnv.get(value);

            if (envVar == null || envVar.isBlank()) {
                throw new EnvVariableNotFoundException(value);
            }

            file.put(key, envVar);
        });

        return file;
    }

    public boolean isEnvVariableName(String string) {
        return string.matches("^\\$\\{.+\\}$");
    }
}
