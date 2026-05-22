package com.kaiser.login.service.data;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Fernando Apaza
 * Date: 21/05/2026
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class RegisterUserDTO implements Serializable {
    private String loginName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String documentId;
    private String address;
    private String numberCelular;
    private String areaRole;
    private String roleDescription;
}
