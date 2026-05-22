package com.kaiser.login.service.login.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 15/05/2026
 */
@Setter
@Getter
@Accessors(chain = true)
//@ToString(exclude = {"accessToken", "refreshToken"})
@Schema(description = "Informacion de autorizacion", required = true)
public class AuthInfo implements Serializable {
    static final long serialVersionUID = 1L;

    @Schema(description = "Identificador de sesion")
    private String session;
    @Schema(description = "Access Token")
    private String accessToken;
    @Schema(description = "Tiempo de expiracion de Access Token en segundos")
    private Long expiresIn;
    @Schema(description = "Refresh Token")
    private String refreshToken;
    @Schema(description = "Tiempo de expiracion de Refresh Token en segundos")
    private int refreshExpiresIn;
}

