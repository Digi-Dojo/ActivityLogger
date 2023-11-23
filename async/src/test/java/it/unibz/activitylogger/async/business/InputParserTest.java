package it.unibz.activitylogger.async.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unibz.activitylogger.async.api.InvalidInputMessageException;
import it.unibz.activitylogger.core.api.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {
    private InputParser underTest;

    @BeforeEach
    void setup() {
        underTest = new InputParser();
    }

    @Test
    void givenProperJson_thenItWorks() {
        // given
        String method = "post";
        String path = "/foo/bar";
        String bodyKey = "foo";
        String bodyValue = "bar";
        Map<String, String> body = Map.of(bodyKey, bodyValue);
        String json = getJson(method, path, body);

        // when
        Input input = underTest.from(json);

        // then
        assertEquals(method, input.method());
        assertEquals(path, input.uri());
        assertInstanceOf(Map.class, input.body());
    }

    @Test
    void givenInvalidJson_thenItThrows() {
        // given
        String json = "{}";

        // when ... then
        assertThrows(InvalidInputMessageException.class, () -> {
            underTest.from(json);
        });
    }

    private static String getJson(String method, String path, Map<String, String> body) {
        Input input = new Input(method, path, body);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(input);
    }
}