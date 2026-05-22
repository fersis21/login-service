package com.kaiser.login.service.login.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 27/04/2026
 */
@Setter
@Getter
@ToString(exclude = {"password"})
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request de validacion de Credenciales asignadas al usuario")
public class ValidateCredentialRequest implements Serializable {

    @NotBlank(message = "Login Name Invalido.")
    @Schema(description = "LoginName de cliente registrado", required = true, example = "fapaza")
    private String loginName;

    @Schema(description = "Password a ser validado", required = true, example = "370822")
    private String password;
}
