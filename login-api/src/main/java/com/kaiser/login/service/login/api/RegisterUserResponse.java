package com.kaiser.login.service.login.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 21/05/2026
 */
@Getter
@Setter
@Accessors(chain = true)
public class RegisterUserResponse implements Serializable {
    @Schema(description = "Codigo de Cliente de COBIS.", example = "123")
    private String clientId;

    @Schema(description = "Nombre de usuario.", example = "Luis")
    private String firstName;
}
