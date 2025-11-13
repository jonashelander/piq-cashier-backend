package com.example.PIQResponseMock.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogJsonHelper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    // Always use this method for JsonNode: ensures the log time field is first
    public static JsonNode addLogTime(JsonNode node, String fieldName) {
        if (node == null || node.isNull()) return node;
        ObjectNode objectNode = node.deepCopy();
        objectNode.remove(fieldName);
        ObjectNode ordered = objectNode.objectNode();
        ordered.put(fieldName, LocalDateTime.now().format(FORMATTER));
        objectNode.fieldNames().forEachRemaining(f -> ordered.set(f, objectNode.get(f)));
        return ordered;
    }

    // Always use this method for POJOs: ensures the log time field is first
    public static <T> T addLogTimeToPojo(T pojo, Class<T> clazz, String fieldName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.valueToTree(pojo);
            node.remove(fieldName);
            ObjectNode ordered = node.objectNode();
            ordered.put(fieldName, LocalDateTime.now().format(FORMATTER));
            node.fieldNames().forEachRemaining(f -> ordered.set(f, node.get(f)));
            return mapper.treeToValue(ordered, clazz);
        } catch (Exception e) {
            return pojo;
        }
    }
}

