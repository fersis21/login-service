package com.kaiser.login.service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "area_rol", nullable = false, length = 50)
    private String areaRol;

    @Size(max = 80)
    @NotNull
    @Column(name = "descripcion_rol", nullable = false, length = 80)
    private String descripcionRol;

    @Column(name = "estado", columnDefinition = "bpchar(1)")
    private String estado;

    @Column(name = "fecha_creacion")
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private OffsetDateTime fechaActualizacion;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();

    // Constructor útil
    public RolEntity(Long id) {
        this.id = Math.toIntExact(id);
    }
    public RolEntity(String area_rol, String descripcionRol) {
        this.areaRol = area_rol;
        this.descripcionRol = descripcionRol;
    }
}