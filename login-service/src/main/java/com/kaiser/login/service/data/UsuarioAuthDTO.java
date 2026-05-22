package com.kaiser.login.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 09/05/2026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAuthDTO implements Serializable {

            private Integer id;
            private String usuario;
            private String email;
            private String password;  // Solo para validación
            private Boolean activo;
            private Long verificado;
            private String intentoLoginFallido;
            private String userName;
            private String rolUser;

}
