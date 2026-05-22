package com.kaiser.login.service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "funcionarios")
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Integer id;

    /*@NotNull
    @Column(name = "id_rol", nullable = false)
    private Integer idRol;*/

    @Size(max = 80)
    @NotNull
    @Column(name = "nombres", nullable = false, length = 80)
    private String nombres;

    @Size(max = 50)
    @NotNull
    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Size(max = 50)
    @Column(name = "apellido_materno", length = 50)
    private String apellidoMaterno;

    @Size(max = 20)
    @NotNull
    @Column(name = "numero_ci", nullable = false, length = 20)
    private String numeroCi;

    @Size(max = 250)
    @Column(name = "direccion", length = 250)
    private String direccion;

    @Size(max = 30)
    @Column(name = "numero_celular", length = 30)
    private String numeroCelular;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "estado", columnDefinition = "bpchar(1)")
    private String estado;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", insertable = false, updatable = false)
    private UsuarioEntity usuario;*/
    // ✅ Relación OneToOne con Usuario (lado inverso)
    @OneToOne(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy para no cargar el rol si no lo necesitas
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity role;

    // Método helper para verificar si tiene usuario asociado
    public boolean tieneUsuario() {
        return usuario != null;
    }

    // Constructor útil
    public FuncionarioEntity(Long id) {
        this.id = Math.toIntExact(id);
    }

    // Método helper para obtener nombre completo
    public String getnombres() {
        return nombres;
    }
}