package com.kaiser.login.service.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaiser.login.service.login.api.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author Fernando Apaza
 * Date: 27/04/2026
 */
@Component
public class Convert<T> {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Convert T Objecto to byte array
     *
     * @param object Object to convert
     * @return byte[]
     * @throws CustomException on any exception
     */
    public byte[] objectToBytes(T object) throws CustomException {
        ObjectOutputStream out = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteOut);
            out.writeObject(object);
            return byteOut.toByteArray();
        } catch (IOException ex) {
            throw new CustomException("Exception on conver Object[" + object.getClass().getSimpleName() + "] to byte[]", ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    //log.severe(ex.getMessage());
                }
            }
        }
    }

    /**
     * Convert a byte array to T Object
     *
     * @param bs byte array to convert
     * @return T Class
     * @throws CustomException on any exception
     */
    public T bytesToObject(byte[] bs) throws CustomException {
        ObjectInputStream in = null;
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(bs);
            in = new ObjectInputStream(byteIn);
            T object = (T) in.readObject();
            return object;
        } catch (IOException | ClassNotFoundException ex) {
            throw new CustomException("Exception on conver byte[] to Object", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    //log.severe(ex.getMessage());
                }
            }
        }
    }

    /**
     * Convert T Object to String Base64
     *
     * @param object T Object
     * @return String Base 64
     * @throws CustomException on any exception
     */
    public String objectToBase64String(T object) throws CustomException {
        return Base64.getEncoder().encodeToString(objectToBytes(object));
    }

    /**
     * Convert String Base 64 to Object T
     *
     * @param base64Object String Base 64 to convert
     * @return T Object
     * @throws CustomException on any exception
     */
    public T base64StringToObject(String base64Object) throws CustomException {
        return bytesToObject(Base64.getDecoder().decode(base64Object));
    }

    /**
     * Convert Object to JSON String
     *
     * @param object Object to convert
     * @return String as JSON structure
     * @throws CustomException on any exception
     */
    public String objectToJSONString(Object object) throws CustomException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new CustomException("Exception on parse Object to JSON String", ex);
        }
    }

    /**
     * Convert a String Object to T Object by a Class&lt;T&gt; reference
     *
     * @param object Object to convert
     * @param tClass Class reference
     * @return T Object
     * @throws CustomException on any exception
     */
    public T jsonToObject(String object, Class<T> tClass) throws CustomException {
        try {
            return objectMapper.readValue(object, tClass);
        } catch (JsonProcessingException ex) {
            throw new CustomException("Exception on parse Object to JSON String", ex);
        }
    }

    /**
     * Convert String as JSON object to T Object by a TypeReference&lt;T&gt;
     *
     * @param object Object to convert
     * @param typeReference TypeReference&lt;T&gt;
     * @return T Object
     * @throws CustomException on any exception
     */
    public T jsonToObject(String object, TypeReference<T> typeReference) throws CustomException {
        try {
            return objectMapper.readValue(object, typeReference);
        } catch (JsonProcessingException ex) {
            throw new CustomException("Exception on parse Object to JSON String", ex);
        }
    }

    /**
     * Convert String as JSON object to T Object by the T Class defined on Convert&lt;T&gt; declaration
     *
     * @param object Object to convert
     * @return T Object
     * @throws CustomException on any exception
     */
    public T jsonToObject(String object) throws CustomException {
        return jsonToObject(object, new TypeReference<T>() {
        });
    }

    /**
     * Convert String as JSON object to HashMap
     *
     * @param stringObject String as JSON object
     * @return HashMap&lt;String, Object&gt;
     * @throws CustomException on any exception
     */
    public HashMap<String, Object> stringObjectToHashMap(String stringObject) throws CustomException {
        try {
            TypeReference<HashMap<String, Object>> reference = new TypeReference<HashMap<String, Object>>() {
            };
            return objectMapper.readValue(stringObject, reference);
        } catch (JsonProcessingException ex) {
            throw new CustomException("Exception on parse Object to JSON String", ex);
        }
    }

    /**
     * Convert a Object referenced by a Class&lt;T&gt;
     *
     * @param object Object to convert
     * @param tClass Class&lt;T&gt;
     * @return T Object
     */
    public T convertObject(Object object, Class<T> tClass) {
        return objectMapper.convertValue(object, tClass);
    }

    /**
     * Convert a Object to T Object by a TypeReference&lt;T&gt;
     *
     * @param object Object to convert
     * @param typeReference TypeReference&lt;T&gt;
     * @return T Object
     */
    public T convertObject(Object object, TypeReference<T> typeReference) {
        return objectMapper.convertValue(object, typeReference);
    }

    /**
     * Converts a string object to a JsonNode object; see the JsonNode documentation
     *
     * @param object Object to convert
     * @return JsonNode represent the input object as JsonNode object
     * @throws CustomException when the parse process generate an exception
     */
    public JsonNode convertObject(String object) throws CustomException {
        try {
            return objectMapper.readTree(object);
        } catch (Exception ex) {
            throw new CustomException("Exception on parse Object to JSON String", ex);
        }
    }

    /**
     * Converts a Object to a JsonNode object; see the JsonNode documentation
     *
     * @param object Object to convert
     * @return JsonNode represent the input object as JsonNode object
     * @throws CustomException when the parse process generate an exception
     */
    public JsonNode convertObject(Object object) throws CustomException {
        try {
            return objectMapper.convertValue(object, JsonNode.class);
        } catch (Exception ex) {
            throw new CustomException("Exception on parse Object to JSON String", ex);
        }
    }

    /**
     * Converts a Object to a JsonTool object;
     *
     * @param object Object to convert
     * @return JsonNode represent the input object as JsonNode object
     * @throws CustomException when the parse process generate an exception
     */
    public JsonTool convertObjectToJsonTool(Object object) throws CustomException {
        try {
            return new JsonTool(objectMapper.convertValue(object, JsonNode.class));
        } catch (Exception ex) {
            throw new CustomException("Exception on parse Object to JSON String", ex);
        }
    }
}
