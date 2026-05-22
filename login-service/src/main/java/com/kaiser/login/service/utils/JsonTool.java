package com.kaiser.login.service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.kaiser.login.service.login.api.CustomException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Fernando Apaza
 * Date: 28/04/2026
 */
@Getter
@Setter
public class JsonTool {
    @Getter
    @Setter
    public class JsonInfo {

        private JsonNode node;
        private String attribute;
    }

    private JsonNode jsonNode;

    /**
     * JsonTool constructor
     *
     * @param jsonNode JsonNode Object
     */
    public JsonTool(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    /**
     * Get the value of a JsonNode object as a String
     *
     * @param param Attribute name
     * @return String
     * @throws CustomException on exception if the object or attribute cannot be processed
     */
    public String getStringValue(String param) throws CustomException {
        JsonInfo jsonInfo = get(param);
        if (jsonInfo.getNode() == null) {
            return null;
        }
        if (jsonInfo.getNode().getNodeType() == JsonNodeType.STRING) {
            return jsonInfo.getNode().textValue();
        } else {
            return jsonInfo.getNode().toString();
        }
    }

    /**
     * Get the value of a JsonNode object as a Boolean
     *
     * @param param Attrbute name
     * @return Boolean
     * @throws CustomException on exception if the object or attribute cannot be processed
     */
    public Boolean getBooleanValue(String param) throws CustomException {
        JsonInfo jsonInfo = get(param);
        if (jsonInfo.getNode() == null) {
            return null;
        }
        if (jsonInfo.getNode().getNodeType() == JsonNodeType.BOOLEAN) {
            return jsonInfo.getNode().booleanValue();
        } else {
            try {
                return Boolean.valueOf(jsonInfo.getNode().toString());
            } catch (Exception e) {
                throw new CustomException("The value of [" + jsonInfo.getAttribute() + "] is not a Boolean");
            }
        }
    }

    /**
     * Get the value of a JsonNode object as a BigInteger
     *
     * @param param Attribute name
     * @return BigInteger
     * @throws CustomException on exception if the object or attribute cannot be processed
     */
    public BigInteger getBigIntegerValue(String param) throws CustomException {
        JsonInfo jsonInfo = get(param);
        if (jsonInfo.getNode() == null) {
            return null;
        }
        if (jsonInfo.getNode().getNodeType() == JsonNodeType.NUMBER) {
            return jsonInfo.getNode().bigIntegerValue();
        } else {
            try {
                return BigInteger.valueOf(Long.valueOf(jsonInfo.getNode().toString()));
            } catch (Exception e) {
                throw new CustomException("The value of [" + jsonInfo.getAttribute() + "] is not a Number");
            }
        }
    }

    /**
     * Get the value of a JsonNode object as a BigDecimal
     *
     * @param param Attribute name
     * @return BigDecimal
     * @throws CustomException on exception if the object or attribute cannot be processed
     */
    /*public BigDecimal getAmountValue(String param) throws CustomException {
        JsonInfo jsonInfo = get(param);
        if (jsonInfo.getNode() == null) {
            return null;
        }
        return Amount.getAmount(jsonInfo.getNode().textValue());
    }*/

    /**
     * Get the value of a JsonNode object as a ArrayNode
     *
     * @param param Attribute name
     * @return ArrayNode
     * @throws CustomException on exception if the object or attribute cannot be processed
     */
    public ArrayNode getArrayNode(String param) throws CustomException {
        try {
            JsonInfo jsonInfo = get(param);
            if (jsonInfo.getNode() == null) {
                return null;
            }
            if (jsonInfo.getNode().getNodeType() == JsonNodeType.ARRAY) {
                return (ArrayNode) jsonInfo.getNode();
            } else {
                throw new CustomException("The value of [" + jsonInfo.getAttribute() + "] is not an array");
            }
        } catch (Exception ex) {
            throw new CustomException("Error on getValue", ex);
        }

    }

    /**
     * Get the value of a JsonNode object as a JsonInfo
     *
     * @param param Attribute name
     * @return JsonInfo
     * @throws CustomException on exception if the object or attribute cannot be processed
     */
    public JsonInfo get(String param) throws CustomException {
        JsonInfo jsonInfo = new JsonInfo();
        String[] attributes = param.split("\\.");

        JsonNode node = jsonNode;
        String lastAttr = "";
        for (String attribute : attributes) {
            lastAttr = attribute;
            node = node.get(attribute);
            if (node == null) {
                break;
            }
        }

        jsonInfo.setNode(node);
        jsonInfo.setAttribute(lastAttr);

        return jsonInfo;
    }
}
