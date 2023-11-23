package it.unibz.activitylogger.main;

import it.unibz.activitylogger.main.configs.ConfigLoader;
import it.unibz.activitylogger.main.configs.EnvVariableNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Properties;

class ConfigLoaderTest {
    private ConfigLoader underTest;

    @BeforeEach
    void setup() {
        underTest = new ConfigLoader();
    }

    @Test
    void whenGivenPropertiesFromEnvAndFile_thenItMergesThem() {
        // given
        String propertyName = "foo";
        String envVar = "APP_FOO";
        String envVarIndicator = ConfigLoader.indicateAsEnvVar(envVar);
        String envVarValue = "bar";

        Properties file = new Properties();
        file.put(propertyName, envVarIndicator);
        Map<String, String> env = Map.of(envVar, envVarValue);

        // when
        Properties result = underTest.mergeEnvIntoConfigs(env, file);

        // then
        Assertions.assertEquals(envVarValue, result.getProperty(propertyName));
    }

    @Test
    void whenUnprovidedEnvVar_thenItThrows() {
        // given
        String propertyName = "foo";
        String envVar = "APP_FOO";
        String envVarIndicator = ConfigLoader.indicateAsEnvVar(envVar);

        Properties file = new Properties();
        file.put(propertyName, envVarIndicator);
        Map<String, String> env = Map.of();

        // when ... then
        Assertions.assertThrows(EnvVariableNotFoundException.class, () -> {
            underTest.mergeEnvIntoConfigs(env, file);
        });
    }
}