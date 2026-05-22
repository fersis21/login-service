package com.kaiser.login.service.services;

import com.kaiser.login.service.data.UsuarioAuthDTO;
import com.kaiser.login.service.entities.UsuarioEntity;
import com.kaiser.login.service.login.api.*;
import com.kaiser.login.service.login.api.BaseResponse;
import com.kaiser.login.service.login.api.CustomException;
import com.kaiser.login.service.repository.SessionService;
import com.kaiser.login.service.repository.UserRepository;
import com.kaiser.login.service.security.JwtService;
import com.kaiser.login.service.services.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Fernando Apaza
 * Date: 24/04/2026
 */
@Service
public class LoginService implements LoginApi {
    @Autowired
    private SessionService sessionService;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public JwtService jwtService;
    @Override
    public ResponseEntity<BaseResponse<ValidateUserResponse>> validateUser(ValidateUserRequest request) throws CustomException {
        //ValidateUserResponse validateUserResponse = userService.findImageByLoginName(request.getLoginName());
        ValidateUserResponse validateUserResponse=new ValidateUserResponse();
        validateUserResponse.setType("N");
        return ok(new BaseResponse().setResult(validateUserResponse));
    }
    @Override
    public ResponseEntity<BaseResponse<ValidateCredentialResponse>> validateCredential(ValidateCredentialRequest request) throws CustomException {
        ValidateCredentialResponse userResponse= new ValidateCredentialResponse();
        UsuarioAuthDTO userDTO= sessionService.findBySession(request.getLoginName());
        //String passAux= passwordEncoder.encode("admin");

        // ✅ Verificar contraseña usando BCrypt (compara hash)
        if (!passwordEncoder.matches(request.getPassword(), userDTO.getPassword())) {
            //log.warn("Contraseña incorrecta para usuario: {}", request.usuario());
            throw new CustomException("Usuario o contraseña incorrectos", HttpStatus.BAD_REQUEST);
        }
        userResponse.setUserInfo(SessionMapper.mapperToUserInfo(userDTO));
        // ✅ Generar JWT token (NO contiene la contraseña)
        String token = jwtService.generateToken(userDTO.getUserName(),userDTO.getId(),userDTO.getRolUser());
        userResponse.setAuthInfo(SessionMapper.mapperToAuthInfo(token,86400000L));
        return ok(new BaseResponse().setResult(userResponse));
    }
    //@Override
    public ResponseEntity<BaseResponse<RegisterUserResponse>> registerUser(RegisterUserRequest request) throws CustomException {
        RegisterUserResponse registerResponse= new RegisterUserResponse();

        return ok(new BaseResponse().setResult(registerResponse));
    }
}

