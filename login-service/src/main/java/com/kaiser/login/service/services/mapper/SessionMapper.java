package com.kaiser.login.service.services.mapper;

import com.kaiser.login.service.data.RegisterUserDTO;
import com.kaiser.login.service.data.UsuarioAuthDTO;
import com.kaiser.login.service.entities.FuncionarioEntity;
import com.kaiser.login.service.entities.UsuarioEntity;
import com.kaiser.login.service.login.api.AuthInfo;
import com.kaiser.login.service.login.api.UserInfo;

/**
 * @author Fernando Apaza
 * Date: 10/05/2026
 */
public class SessionMapper {
    public static UsuarioAuthDTO mapperToSession(UsuarioEntity from){
        UsuarioAuthDTO to= new UsuarioAuthDTO();
        mapperToSession(from,to);
        return to;

    }
    public static void mapperToSession(UsuarioEntity from, UsuarioAuthDTO to){
        to.setId(from.getId());
        to.setUsuario(from.getUsuario());
        to.setEmail(from.getEmail());
        to.setPassword(from.getPasswordHash());
        to.setUserName(from.getFuncionario().getnombres());
        to.setRolUser(from.getFuncionario().getRole().getDescripcionRol());
    }
    public static UserInfo mapperToUserInfo(UsuarioAuthDTO from){
        UserInfo to= new UserInfo();
        mapperToUserInfo(from,to);
        return to;
    }
    public static void mapperToUserInfo(UsuarioAuthDTO from, UserInfo to){
        to.setId(from.getId());
        to.setLoginName(from.getUsuario());
        to.setFirstName(from.getUserName());
        to.setEmail(from.getEmail());
    }
    public static AuthInfo mapperToAuthInfo(String token, Long expiration ){
        AuthInfo to= new AuthInfo();
        mapperToAuthInfo(token,expiration,to);
        return to;
    }
    public static void mapperToAuthInfo(String token, Long expiration, AuthInfo to){
        to.setAccessToken(token);
        to.setExpiresIn(expiration);
    }
    public static UsuarioEntity mapperToRegisteruser(RegisterUserDTO from) {
        UsuarioEntity to = new UsuarioEntity();
        mapperToRegisteruser(from, to);
        return to;
    }
    public static void mapperToRegisteruser(RegisterUserDTO from, UsuarioEntity to) {
        to.setUsuario(from.getLoginName());
        to.setEmail(from.getEmail());
        to.setPasswordHash(from.getPassword());
        to.setActivo(true);
        to.setVerificado(true);

        to.setDevice(DeviceMapper.mapperToDeviceEntity(from.getDevice()));
        to.setLastTokenId(from.getLastTokenId());
    }
    public static FuncionarioEntity mapperToFuncionario(){

    }
}
