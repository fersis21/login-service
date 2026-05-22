package com.kaiser.login.service.login.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 27/04/2026
 */
@Setter
@Getter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Informacion de usuario registrado.")
public class ValidateCredentialResponse implements Serializable {

    @Schema(description = "Informacion de Usuario")
    private UserInfo userInfo;

    @Schema(description = "Informacion de Autorizacion")
    private AuthInfo authInfo;

}
