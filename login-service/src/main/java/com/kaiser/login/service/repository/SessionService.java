package com.kaiser.login.service.repository;

import com.kaiser.login.service.data.RegisterUserDTO;
import com.kaiser.login.service.data.UsuarioAuthDTO;
import com.kaiser.login.service.entities.FuncionarioEntity;
import com.kaiser.login.service.entities.UsuarioEntity;
import com.kaiser.login.service.login.api.CustomException;
import com.kaiser.login.service.login.api.RegisterUserResponse;
import com.kaiser.login.service.services.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Fernando Apaza
 * Date: 10/05/2026
 */
@Service
public class SessionService {
    @Autowired
    private UserRepository userRepository;

    public UsuarioAuthDTO findBySession (String userLogin) throws CustomException{
        UsuarioEntity userEntity = userRepository
                .findByLoginName(userLogin)
                .orElseThrow(() -> new CustomException("Usuario no encontrado.", HttpStatus.BAD_REQUEST));
        return SessionMapper.mapperToSession(userEntity);
    }
    public RegisterUserDTO saveUser(RegisterUserDTO dto) throws CustomException{
        UsuarioEntity entity =
    }
}
