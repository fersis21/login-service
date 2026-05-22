package com.kaiser.login.service.controller;

import com.kaiser.login.service.login.api.*;
import com.kaiser.login.service.login.api.BaseResponse;
import com.kaiser.login.service.login.api.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

/**
 * @author Fernando Apaza
 * Date: 24/04/2026
 */
@RestController
@RequestMapping(path = "/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoginController {
    @Autowired
    private LoginApi service;

    @PostMapping("/validateUser")
    @Operation(summary = "Valida si el usuario existe y retorna su imagen de seguridad", tags = {"Login"})
    public ResponseEntity<BaseResponse<ValidateUserResponse>> validateUser(@RequestBody @Valid ValidateUserRequest request) throws CustomException {
        return service.validateUser(request);
    }

    @PostMapping("/validateCredential")
    @Operation(summary = "Valida si el usuario existe y retorna su imagen de seguridad", tags = {"Login"})
    public ResponseEntity<BaseResponse<ValidateCredentialResponse>> validateCredential(@RequestBody @Valid ValidateCredentialRequest request) throws CustomException {
        return service.validateCredential(request);
    }


}
