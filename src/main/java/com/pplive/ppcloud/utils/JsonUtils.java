/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.utils;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chaogao
 *
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    private static final ObjectWriter writer = objectMapper.writer();
    private static final ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();

    public static String toJsonPrettyString(Object value) throws JsonProcessingException {
        return JsonUtils.prettyWriter.writeValueAsString(value);
    }

    public static String toJsonString(Object value) {
        try {
            return JsonUtils.writer.writeValueAsString(value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns the deserialized object from the given json string and target class; or null if the given json string is null.
     * 
     * @param <T> 声明泛型
     * @param json json字符串
     * @param clazz 类声明
     * @return 指定的类对象
     */
    public static <T> T fromJsonString(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static JsonNode jsonNodeOf(String json) {
        return JsonUtils.fromJsonString(json, JsonNode.class);
    }

    public static JsonGenerator jsonGeneratorOf(Writer writer) throws IOException {
        return new JsonFactory().createGenerator(writer);
    }

    public static <T> T loadFrom(File file, Class<T> clazz) throws IOException {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void load(InputStream input, Object obj) throws IOException, JsonProcessingException {
        objectMapper.readerForUpdating(obj).readValue(input);
    }

    public static <T> T loadFrom(InputStream input, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(input, clazz);
    }
    
    public static <T> List<T> loadListFrom(InputStream input, Class<T> clazz)
    		throws JsonParseException, JsonMappingException, IOException {
    	JsonParser e = objectMapper.getFactory().createParser(input);
        JsonNode nodes = e.readValueAsTree();
        List<T> list = new LinkedList<T>();
        for (JsonNode node : nodes) {
			list.add(objectMapper.readValue(node.asText(), clazz));
		}
        return list;
	}

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ObjectWriter getWriter() {
        return JsonUtils.writer;
    }

    public static ObjectWriter getPrettywriter() {
        return JsonUtils.prettyWriter;
    }
}
