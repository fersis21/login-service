package com.kaiser.login.service.login.api;

import org.springframework.http.ResponseEntity;
/**
 * @author Fernando Apaza
 * Date: 24/04/2026
 */
public interface LoginApi {
    /**
     * validate User Login
     *
     */
    ResponseEntity<BaseResponse<ValidateUserResponse>> validateUser(ValidateUserRequest request) throws CustomException;

    /**
     * validate credential
     *
     */
    ResponseEntity<BaseResponse<ValidateCredentialResponse>> validateCredential(ValidateCredentialRequest request) throws CustomException;
}
