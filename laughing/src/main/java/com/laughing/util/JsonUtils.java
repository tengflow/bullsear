package com.laughing.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.validator.util.privilegedactions.NewInstance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jsonson utils
 * 
 * @see http://jackson.codehaus.org/
 * @see https://github.com/FasterXML/jackson
 * @see http://wiki.fasterxml.com/JacksonHome
 * @author magic_yy
 * 
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json string convert to map
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> json2map(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
        Map<String, Map<String, Object>> map;
        try {
            map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
            });
            Map<String, T> result = new HashMap<String, T>();
            for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
                result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        List<Map<String, Object>> list;
        try {
            list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
            });
            List<T> result = new ArrayList<>();
            for (Map<String, Object> map : list) {
                result.add(map2pojo(map, clazz));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * map convert to javaBean
     */
    @SuppressWarnings("rawtypes")
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        try {
            return objectMapper.convertValue(map, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * pojo convert to map
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> pojo2map(Object o) {
        try {
            return objectMapper.convertValue(o, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // /**
    // * json string convert to xml string
    // */
    // public static String json2xml(String jsonStr)throws Exception{
    // JsonNode root = objectMapper.readTree(jsonStr);
    // String xml = xmlMapper.writeValueAsString(root);
    // return xml;
    // }
    //
    // /**
    // * xml string convert to json string
    // */
    // public static String xml2json(String xml)throws Exception{
    // StringWriter w = new StringWriter();
    // JsonParser jp = xmlMapper.getFactory().createParser(xml);
    // JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
    // while (jp.nextToken() != null) {
    // jg.copyCurrentEvent(jp);
    // }
    // jp.close();
    // jg.close();
    // return w.toString();
    // }

}