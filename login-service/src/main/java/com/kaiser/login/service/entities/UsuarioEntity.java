package com.kaiser.login.service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;


    /*
    @NotNull
    @Column(name = "id_funcionario", nullable = false)
    private Integer idFuncionario;*/

    @Size(max = 50)
    @NotNull
    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "verificado")
    private Boolean verificado;

    @ColumnDefault("0")
    @Column(name = "intento_login_fallido")
    private Integer intentoLoginFallido;

    @Size(max = 255)
    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "refresh_token_expires_at")
    private OffsetDateTime refreshTokenExpiresAt;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;

    @Column(name = "ultimo_login")
    private OffsetDateTime ultimoLogin;

    @Column(name = "ultimo_ip_ingreso")
    private InetAddress ultimoIpIngreso;

    /*@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FuncionarioEntity funcionarioEntity;*/
    // ✅ CORRECTO: Mantén la relación LAZY
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", unique = true)
    private FuncionarioEntity funcionario;

    // Constructor útil
    public UsuarioEntity(String usuario, String password) {
        this.usuario = usuario;
        this.passwordHash = password;
        this.activo = true;
        //this.fechaCreacion = LocalDateTime.now();
    }

}