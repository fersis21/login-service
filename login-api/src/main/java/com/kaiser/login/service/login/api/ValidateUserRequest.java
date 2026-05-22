package com.kaiser.login.service.login.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 24/04/2026
 */

@Getter
@Setter
@Accessors(chain = true)
public class ValidateUserRequest implements Serializable {
    @NotBlank
    @Schema(description = "Nombre del susuario")
    private String loginName;
}
