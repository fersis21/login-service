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
@ToString
@Schema(description = "Informacion de usuario")
public class UserInfo implements Serializable {
    @Schema(description = "Id de registro de usuario.", example = "123")
    private Integer id;

    @Schema(description = "Codigo de LOGIN.", example = "123")
    private String loginName;

    @Schema(description = "Nombre de usuario.", example = "Luis")
    private String firstName;

    @Schema(description = "Apellido de usuario.", example = "Perez")
    private String lastName;

    @Schema(description = "Correo electronico.", example = "lpez@gmail.com")
    private String email;

    @Schema(description = "Numero Telefonico.", example = "74589745")
    private String phoneNumber;

    @Schema(description = "Fecha de creacion. dd/mm/yyyy", example = "14/05/2021")
    private String creationDate;

    @Schema(description = "Fecha de ultima actualicion. dd/mm/yyyy", example = "14/05/2022")
    private String lastUpdated;

    @Schema(description = "Estado de registro.", example = "A")
    private String state;

    @Schema(description = "Cedula de identidad.", example = "465835436")
    private String documentId;
}
