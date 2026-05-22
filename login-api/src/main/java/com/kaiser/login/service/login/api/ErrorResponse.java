package com.kaiser.login.service.login.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 28/04/2026
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ErrorResponse implements Serializable {

    static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private String cause;
    private String reference;
    private String transactionId;

}
