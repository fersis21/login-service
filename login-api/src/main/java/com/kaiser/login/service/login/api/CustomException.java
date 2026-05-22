package com.kaiser.login.service.login.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * @author Fernando Apaza
 * Date: 24/04/2026
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class CustomException extends Exception {
    private HttpStatus httpStatus;
    private String urlReference;
    private String code;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;

    }

    public CustomException(String message, Throwable thrwbl) {
        super(message, thrwbl);
    }

    public CustomException(String message, HttpStatus httpStatus, Throwable thrwbl) {
        super(message, thrwbl);
        this.httpStatus = httpStatus;
    }
}
