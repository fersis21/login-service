package com.kaiser.login.service.repository;

import com.kaiser.login.service.entities.UsuarioEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Fernando Apaza
 * Date: 09/05/2026
 */
@Repository
public interface UserRepository extends JpaRepository<UsuarioEntity, Serializable> {

    @Query("""
        SELECT u FROM UsuarioEntity u 
        LEFT JOIN FETCH u.funcionario f
        LEFT JOIN FETCH f.role 
        WHERE u.usuario = :usuario
    """)
    Optional<UsuarioEntity> findByLoginName(@Param("usuario") String loginUsuario);
}
