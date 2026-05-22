package com.kaiser.login.service.login.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 21/05/2026
 */
@Setter
@Getter
//@ToString(exclude = "userImageCode")
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Informacion de usuario registrado en banca digital")
public class RegisterUserRequest implements Serializable {

    @Schema(description = "Codigo de LOGIN.", example = "123")
    private String loginName;

    @Schema(description = "password.", example = "admin123")
    private String password;

    @Schema(description = "Nombre de usuario.", example = "Luis")
    private String firstName;

    @Schema(description = "Apellido de usuario.", example = "Perez")
    private String lastName;

    @Schema(description = "Correo electronico.", example = "lpez@gmail.com")
    private String email;

    @Schema(description = "Cedula de identidad.", example = "465835436")
    private String documentId;

    @Schema(description = "Direccion.", example = "av. 6 de junio c. junin 1542")
    private String address;

    @Schema(description = "Numero de celular.", example = "73247852")
    private String numberCelular;

    @Schema(description = "Area de rol.", example = "Academico")
    private String areaRole;

    @Schema(description = "Descripcion de rol.", example = "Caja")
    private String roleDescription;
}
