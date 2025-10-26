package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class ObjectMapperUtils {

    public static JsonNode getJsonNode(String fileName) {
        try {
            return new ObjectMapper().readTree(new File("src/test/resources/test_data/" + fileName + ".json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setJsonNode(JsonNode payload, String key, String newValue) {
        ((ObjectNode) payload).put(key, newValue);
    }

    public static void removeField(JsonNode payload, String key) {
        ((ObjectNode) payload).remove(key);
    }

}