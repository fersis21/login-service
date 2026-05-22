package com.kaiser.login.service.login.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Fernando Apaza
 * Date: 24/04/2026
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseResponse<T> implements Serializable {

    private String transactionId;
    private T result;
    private Date timestamp;

    public BaseResponse() {
        this.timestamp = new Date();
        this.transactionId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "BaseResponse{" + "transactionId=" + transactionId + ", result=" + result + ", timestamp=" + timestamp + '}';
    }
}
