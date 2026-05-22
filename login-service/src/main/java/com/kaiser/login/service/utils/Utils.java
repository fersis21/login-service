package com.kaiser.login.service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.kaiser.login.service.login.api.CustomException;
import com.kaiser.login.service.login.api.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Fernando Apaza
 * Date: 27/04/2026
 */
@Component
public class Utils {
    private final String ERROR_SEPARATOR = ":";

    @Autowired
    private Convert convert;

    public CustomException extractCustomException(String message, Throwable ex) {
        int errorSeparatorIndex = message.indexOf(ERROR_SEPARATOR);
        if (errorSeparatorIndex > 0) {
            try {
                HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(message.substring(0, errorSeparatorIndex - 1).trim()));
                JsonNode jsonNode = convert.convertObject(message.substring(errorSeparatorIndex + 1).trim());
                if (jsonNode.isArray()) {
                    String error = jsonNode.get(0).toString();
                    ErrorResponse errorResponse = (ErrorResponse) convert.jsonToObject(error, ErrorResponse.class);
                    return new CustomException(errorResponse.getMessage(), httpStatus);
                } else {
                    return new CustomException("Error on consume API REST", ex).setHttpStatus(httpStatus);
                }
            } catch (CustomException e) {
                return e;
            }
        }
        return null;
    }
}
