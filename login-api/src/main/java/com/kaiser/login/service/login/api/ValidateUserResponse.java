package com.kaiser.login.service.login.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 24/04/2026
 */
@Getter
@Setter
@Accessors(chain = true)
public class ValidateUserResponse implements Serializable {
    @Schema(description = "Tipo de usuario")
    private  String type;
}
